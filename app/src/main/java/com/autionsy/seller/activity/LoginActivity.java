package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RawRes;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autionsy.seller.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

        username = input_username.getText().toString().trim();
        password = input_password.getText().toString().trim();
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
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
