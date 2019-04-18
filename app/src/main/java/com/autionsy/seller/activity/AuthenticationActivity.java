package com.autionsy.seller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autionsy.seller.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthenticationActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.input_id_number_et)
    EditText input_id_number_et;
    @BindView(R.id.input_business_licence_et)
    EditText input_business_licence_et;

    private String idNumber;
    private String businessLicenceNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_authentication);
        ButterKnife.bind(this);
        // 初始化页面
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.register);

        idNumber = input_id_number_et.getText().toString().trim();
        businessLicenceNum = input_business_licence_et.getText().toString().trim();
    }

    @OnClick({R.id.back_btn,R.id.upload_id_front_layout,R.id.upload_id_back_layout,R.id.upload_business_licence_layout,R.id.authentication_ok_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.upload_id_front_layout:

                break;
            case R.id.upload_id_back_layout:

                break;
            case R.id.upload_business_licence_layout:

                break;
            case R.id.authentication_ok_btn:

                break;
        }
    }

    
}
