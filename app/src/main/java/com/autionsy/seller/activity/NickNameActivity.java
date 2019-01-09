package com.autionsy.seller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autionsy.seller.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NickNameActivity extends BaseActivity{
    @BindView(R.id.title_tv)
    TextView title_tv;

    @BindView(R.id.nick_name_et)
    EditText nick_name_et;

    private String nickName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nick_name);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.nick_name);

        nickName = nick_name_et.getText().toString().trim();
    }

    @OnClick({R.id.back_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }
}
