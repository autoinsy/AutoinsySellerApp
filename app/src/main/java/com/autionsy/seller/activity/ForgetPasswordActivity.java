package com.autionsy.seller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autionsy.seller.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity{
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.forget_input_mobile_phone_num_et)
    EditText forget_input_mobile_phone_num_et;
    @BindView(R.id.forget_input_verify_code_et)
    EditText forget_input_verify_code_et;
    @BindView(R.id.forget_input_password_et)
    EditText forget_input_password_et;

    private String mobilePhoneNum;
    private String verifyCode;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_forget_password);
        ButterKnife.bind(this);
        // 初始化页面
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.forget_password);

        mobilePhoneNum = forget_input_mobile_phone_num_et.getText().toString().trim();
        verifyCode = forget_input_verify_code_et.getText().toString().trim();
        password = forget_input_password_et.getText().toString().trim();
    }

    @OnClick({R.id.back_btn,R.id.forget_get_verify_code_tv,R.id.forget_ok_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.forget_get_verify_code_tv:

                break;
            case R.id.forget_ok_btn:

                break;
        }
    }
}
