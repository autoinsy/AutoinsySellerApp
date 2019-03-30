package com.autionsy.seller.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.RawRes;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.constant.Constant;
import com.autionsy.seller.entity.Login;
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

public class LoginActivity extends BaseActivity {

    @BindView(R.id.back_btn)
    LinearLayout back_btn;
    @BindView(R.id.input_username)
    EditText input_username;
    @BindView(R.id.input_password)
    EditText input_password;
    @BindView(R.id.title_tv)
    TextView title_tv;

    private String username;
    private String password;

    private Intent intent;

    private Login login;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        ButterKnife.bind(this);
        // 初始化页面
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.login_title_text);
        back_btn.setVisibility(View.GONE);
    }

    @OnClick({R.id.forget_password_tv,R.id.user_register_tv,R.id.login_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.forget_password_tv:
                intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.user_register_tv:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn:
                postAsynHttpLogin();
                break;
        }
    }

    private void postAsynHttpLogin(){
        login = new Login();
        username = input_username.getText().toString().trim();
        password = input_password.getText().toString().trim();

        String url = Constant.HTTP_URL + "login";

        Map<String,String> map = new HashMap<>();
        map.put("loginName", username);
        map.put("passWord", password);

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
                        if (isUserNameAndPwdValid()){
                            try {
                                JSONObject jsonObject = new JSONObject(responeString);
                                String resultCode = jsonObject.optString("code");
                                String data = jsonObject.optString("data");
                                String message = jsonObject.optString("message");

                                if("200".equals(resultCode)){
                                    //保存用户名和密码
                                    editor.putString("USER_NAME", username);
                                    editor.putString("PASSWORD", password);
                                    editor.commit();

                                    intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }else if("403".equals(resultCode)){
                                    Toast.makeText(getApplicationContext(),R.string.param_error,Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext(),R.string.login_fail,Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    public boolean isUserNameAndPwdValid() {
        if (input_username.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(),R.string.user_err_name_is_null,Toast.LENGTH_SHORT).show();
            return false;
        } else if (input_password.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(),R.string.password_is_empty,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
