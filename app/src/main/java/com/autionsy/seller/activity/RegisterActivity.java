package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.constant.Constant;
import com.autionsy.seller.utils.CountDownTimerUtils;
import com.autionsy.seller.utils.OkHttp3Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.input_register_username_et)
    EditText input_register_username_et;
    @BindView(R.id.register_password_et)
    EditText register_password_et;
    @BindView(R.id.input_register_verify_code_et)
    EditText input_register_verify_code_et;
    @BindView(R.id.get_verify_code_tv)
    TextView get_verify_code_tv;

    private String username;
    private String password;
    private String verifyCode;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register);
        ButterKnife.bind(this);
        // 初始化页面
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.register);

        username = input_register_username_et.getText().toString().trim();
        password = register_password_et.getText().toString().trim();
        verifyCode = input_register_verify_code_et.getText().toString().trim();
    }

    @OnClick({R.id.back_btn,R.id.register_next_btn,R.id.autoinsy_city_protocol,R.id.get_verify_code_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.register_next_btn:
                getRegister();
                break;
            case R.id.autoinsy_city_protocol:
                intent = new Intent(RegisterActivity.this,ServiceProtocolActivity.class);
                startActivity(intent);
                break;
            case R.id.get_verify_code_tv:
                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(get_verify_code_tv, 60000, 1000);
                mCountDownTimerUtils.start();
                getVerifyCode();
                break;
        }
    }

    private void getRegister(){
        String url = Constant.HTTP_URL + "sellerRegister";
        Map<String,String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("sms_validate_code", verifyCode);

        OkHttp3Utils.doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responeString = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject  = new JSONObject(responeString);
                            String resultCode = jsonObject.optString("code");
                            String data = jsonObject.optString("data");
                            String message = jsonObject.optString("message");

                            if("200".equals(resultCode)){
                                intent = new Intent(RegisterActivity.this,AuthenticationActivity.class);
                                startActivity(intent);
                                finish();
                            }else if("411".equals(resultCode)){
                                Toast.makeText(getApplicationContext(),R.string.user_already_register,Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void getVerifyCode(){

        String url = Constant.HTTP_URL + "sendValidateCode";
        Map<String,String> map = new HashMap<>();
        map.put("phone_number", username);

        OkHttp3Utils.doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responeString = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject  = new JSONObject(responeString);
                            String resultCode = jsonObject.optString("code");
                            String data = jsonObject.optString("data");
                            String message = jsonObject.optString("message");

                            if("200".equals(resultCode)){
                                Toast.makeText(getApplicationContext(),R.string.send_sms_code_success,Toast.LENGTH_SHORT).show();
                            }else if("401".equals(resultCode)){
                                Toast.makeText(getApplicationContext(),R.string.send_sms_code_fail,Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }


}
