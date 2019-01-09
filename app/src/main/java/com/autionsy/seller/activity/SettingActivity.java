package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.autionsy.seller.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.title_tv)
    TextView title_tv;

    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_setting);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.setting_text);

    }

    @OnClick({R.id.back_btn,
            R.id.nick_name_layout,
            R.id.receive_address_layout,
            R.id.bank_account_binding_layout})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.nick_name_layout:
                intent = new Intent(SettingActivity.this, NickNameActivity.class);
                startActivity(intent);
                break;
            case R.id.receive_address_layout:

                break;
            case R.id.bank_account_binding_layout:

                break;
        }
    }


}
