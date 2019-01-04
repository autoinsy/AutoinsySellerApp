package com.autionsy.seller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.scrat.app.selectorlibrary.ImageSelector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishRecruitActivity extends BaseActivity{

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.submit_tv)
    TextView submit_tv;

    @BindView(R.id.recruit_title_et)
    EditText recruit_title_et;
    @BindView(R.id.company_name_et)
    EditText company_name_et;
    @BindView(R.id.start_salary_et)
    EditText start_salary_et;
    @BindView(R.id.end_salary_et)
    EditText end_salary_et;
    @BindView(R.id.seller_address_et)
    EditText seller_address_et;
    @BindView(R.id.recruit_person_number_et)
    EditText recruit_person_number_et;
    @BindView(R.id.contact_phone_number_et)
    EditText contact_phone_number_et;
    @BindView(R.id.work_experience_et)
    EditText work_experience_et;
    @BindView(R.id.education_et)
    EditText education_et;
    @BindView(R.id.company_person_number_et)
    EditText company_person_number_et;

    private String recruitTile;
    private String companyName;
    private String startSalary;
    private String endSalary;
    private String sellerAddress;
    private String recruitPersonNum;
    private String contactPhoneNum;
    private String workExperience;
    private String education;
    private String companyPersonNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_publish_recruit);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.publish_recruit);
        submit_tv.setVisibility(View.VISIBLE);

        recruitTile = recruit_title_et.getText().toString().trim();
        companyName = company_name_et.getText().toString().trim();
        startSalary = start_salary_et.getText().toString().trim();
        endSalary = end_salary_et.getText().toString().trim();
        sellerAddress = seller_address_et.getText().toString().trim();
        recruitPersonNum = recruit_person_number_et.getText().toString().trim();
        contactPhoneNum = contact_phone_number_et.getText().toString().trim();
        workExperience = work_experience_et.getText().toString().trim();
        education = education_et.getText().toString().trim();
        companyPersonNum = company_person_number_et.getText().toString().trim();
    }

    @OnClick({R.id.back_btn,
            R.id.submit_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.submit_tv:

                break;
        }
    }
}
