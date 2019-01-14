package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.autionsy.seller.R;
import com.autionsy.seller.adapter.NoticeAdapter;
import com.autionsy.seller.entity.Notice;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeActivity extends BaseActivity {

    @BindView(R.id.notice_lv)
    ListView notice_lv;
    @BindView(R.id.title_tv)
    TextView title_tv;

    private NoticeAdapter noticeAdapter;
    private List<Notice> mList = new ArrayList<>();

    private String noticeTitle;
    private String noticeContent;
    private String noticeTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_notice);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.notice_center);

        mList.add(new Notice("基于vue-easytable实现数据的增删改查","2018-12-14 10:10:10","设置表格总宽度，最后一列不设置，那么自动一列宽度将会自动计算"));
        mList.add(new Notice("基于vue-easytable实现数据的增删改查","2018-12-14 10:10:10","设置表格总宽度，最后一列不设置，那么自动一列宽度将会自动计算"));
        mList.add(new Notice("基于vue-easytable实现数据的增删改查","2018-12-14 10:10:10","设置表格总宽度，最后一列不设置，那么自动一列宽度将会自动计算"));
        mList.add(new Notice("基于vue-easytable实现数据的增删改查","2018-12-14 10:10:10","设置表格总宽度，最后一列不设置，那么自动一列宽度将会自动计算"));

        noticeAdapter = new NoticeAdapter(NoticeActivity.this,mList);
        notice_lv.setAdapter(noticeAdapter);
        notice_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 实例化一个Bundle
                Bundle bundle = new Bundle();
                Intent intent = new Intent(NoticeActivity.this,NoticeDetailActivity.class);
                bundle.putString("notice_title",noticeTitle);
                bundle.putString("notice_content",noticeContent);
                bundle.putString("notice_time",noticeTime);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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
