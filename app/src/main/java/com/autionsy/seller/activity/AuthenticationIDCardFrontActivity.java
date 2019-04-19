package com.autionsy.seller.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.constant.Constant;
import com.wildma.idcardcamera.camera.CameraActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AuthenticationIDCardFrontActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.input_id_number_et)
    EditText input_id_number_et;

    @BindView(R.id.id_card_front_iv)
    ImageView id_card_front_iv;
    @BindView(R.id.id_card_front_camera_iv)
    ImageView id_card_front_camera_iv;

    private String idNumber;

    private String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_authentication_id_card_front);
        ButterKnife.bind(this);
        // 初始化页面
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.authentication);
    }

    @OnClick({R.id.back_btn,
            R.id.upload_id_front_layout,
            R.id.authentication_id_card_front_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.upload_id_front_layout:
                CameraActivity.toCameraActivity(this, CameraActivity.TYPE_IDCARD_FRONT);
                break;
            case R.id.authentication_id_card_front_btn:
                postUploadFile();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CameraActivity.REQUEST_CODE && resultCode == CameraActivity.RESULT_CODE) {
            //获取图片路径，显示图片
            path = CameraActivity.getImagePath(data);
            if (!TextUtils.isEmpty(path)) {
                id_card_front_iv.setImageBitmap(BitmapFactory.decodeFile(path));
                id_card_front_camera_iv.setVisibility(View.GONE);
            }
        }
    }

    public void postUploadFile() {
        idNumber = input_id_number_et.getText().toString().trim();
        SharedPreferences sharepreferences = getSharedPreferences("seller_login_data", Activity.MODE_PRIVATE);
        String username = sharepreferences.getString("USERNAME", "");

        String url = Constant.HTTP_URL + Constant.UPLOAD_ID_CARD_FRONT;

        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);

        File file = new File(path);

        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("upload_id_card_front_image", filename, body);
        }

        Map<String,String> map = new HashMap<>();
        map.put("id_card_num",idNumber);
        map.put("upload_type","2");
        map.put("username",username);

        //要上传的文字参数
        if (map != null) {
            for (String key : map.keySet()) {
                requestBody.addFormDataPart(key, map.get(key));
            }
        }
        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        // readTimeout("请求超时时间" , 时间单位);
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String str = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                String resultCode = jsonObject.optString("code");
                                String data = jsonObject.optString("data");
                                String message = jsonObject.optString("message");

                                if("200".equals(resultCode)){
                                    Toast.makeText(getApplicationContext(),R.string.upload_success,Toast.LENGTH_SHORT).show();
                                }else if("403".equals(resultCode)){
                                    Toast.makeText(getApplicationContext(),R.string.param_error,Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(),R.string.request_error,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
