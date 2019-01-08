package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.UploadImageAdapter;
import com.autionsy.seller.views.GridViewInScrollView;
import com.scrat.app.selectorlibrary.ImageSelector;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

public class PublishRescueActivity extends BaseActivity {
    @BindView(R.id.upload_image_gv)
    GridViewInScrollView upload_image_gv;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.submit_tv)
    TextView submit_tv;

    @BindView(R.id.rescue_rescue_title_et)
    EditText rescue_rescue_title_et;
    @BindView(R.id.rescue_company_name_et)
    EditText rescue_company_name_et;
    @BindView(R.id.rescue_phone_number_et)
    EditText rescue_phone_number_et;
    @BindView(R.id.rescue_address_detail_et)
    EditText rescue_address_detail_et;
    @BindView(R.id.rescue_service_scope_et)
    EditText rescue_service_scope_et;
    @BindView(R.id.rescue_company_introduce_et)
    EditText rescue_company_introduce_et;

    private String title;
    private String companyName;
    private String phoneNum;
    private String addressDetail;
    private String serviceScope;
    private String companyIntroduce;

    private static final int REQUEST_CODE_SELECT_IMG = 1;
    private static final int MAX_SELECT_COUNT = 9;
    private File file;
    private List<String> path;//路径集合

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_publish_rescue);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.publish_rescue);
        submit_tv.setVisibility(View.VISIBLE);

        title = rescue_rescue_title_et.getText().toString().trim();
        companyName = rescue_company_name_et.getText().toString().trim();
        phoneNum = rescue_phone_number_et.getText().toString().trim();
        addressDetail = rescue_address_detail_et.getText().toString().trim();
        serviceScope = rescue_service_scope_et.getText().toString().trim();
        companyIntroduce = rescue_company_introduce_et.getText().toString().trim();
    }

    @OnClick({R.id.back_btn,
            R.id.image_selector_layout,
            R.id.submit_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.image_selector_layout:
                ImageSelector.show(this, REQUEST_CODE_SELECT_IMG, MAX_SELECT_COUNT);
                break;
            case R.id.submit_tv:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SELECT_IMG) {
            showImage(data); //设置图片 跟图片目录
            uploadImage(data);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadImage(Intent data) {

        if (data != null) {
            path = ImageSelector.getImagePaths(data);
            for (int i = 0; i < path.size(); i++) {
                /*
                 * 从本地文件中读读取图片
                 * */
                String fileName = "";
                file = new File(path.get(i));
                if (file.getName() == null) {
                } else {
                    fileName = getFileName(path.get(i));
                }
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", fileName, RequestBody.create(MediaType.parse("image/jpg"), file))
                        .build();
                Request build = new Request.Builder()
                        .url("http://172.16.52.10:8080/UploadDemo4/UploadFile") //TomCat服务器
                        .post(requestBody)
                        .build();
                new OkHttpClient().newCall(build).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        setResult(response.body().string(), true);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishRescueActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }
        UploadImageAdapter adapter = new UploadImageAdapter(path, PublishRescueActivity.this);
        upload_image_gv.setAdapter(adapter);
    }
    private void showImage(Intent data) {
        path = ImageSelector.getImagePaths(data); //集合获取path(这里的path是集合)
    }

    public String getFileName(String pathandname) {
        int start = pathandname.lastIndexOf("/");
        if (start != -1) {
            return pathandname.substring(start + 1);
        } else {
            return null;
        }
    }
    private void setResult(String string, final boolean success) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (success) {
                    Toast.makeText(PublishRescueActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PublishRescueActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
