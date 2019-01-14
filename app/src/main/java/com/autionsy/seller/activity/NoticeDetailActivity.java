package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.autionsy.seller.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeDetailActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;

    @BindView(R.id.notice_detail_title_tv)
    TextView notice_detail_title_tv;
    @BindView(R.id.notice_detail_time_tv)
    TextView notice_detail_time_tv;
    @BindView(R.id.notice_detail_content_tv)
    TextView notice_detail_content_tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_notice_detail);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.notice_center);

        Bundle bundle = this.getIntent().getExtras();
        String noticeId = bundle.getString("notice_id");
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
