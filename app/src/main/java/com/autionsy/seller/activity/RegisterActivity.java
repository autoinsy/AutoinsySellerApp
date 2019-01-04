package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autionsy.seller.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.input_register_username_et)
    EditText input_register_username_et;
    @BindView(R.id.register_password_et)
    EditText register_password_et;
    @BindView(R.id.input_register_verify_code_et)
    EditText input_register_verify_code_et;

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
                intent = new Intent(RegisterActivity.this,AuthenticationActivity.class);
                startActivity(intent);
                break;
            case R.id.autoinsy_city_protocol:
                intent = new Intent(RegisterActivity.this,ServiceProtocolActivity.class);
                startActivity(intent);
                break;
            case R.id.get_verify_code_tv:

                break;
        }
    }
}
