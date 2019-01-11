package com.autionsy.seller.activity;

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

        mList.add(new Notice("http://pic.sc.chinaz.com/files/pic/pic9/201901/hpic480.jpg","春节放假通知","2019-1-12","春节放假10天"));
        mList.add(new Notice("http://pic.sc.chinaz.com/files/pic/pic9/201803/zzpic11139.jpg","春节放假通知","2019-1-12","春节放假10天"));
        mList.add(new Notice("http://pic.sc.chinaz.com/files/pic/pic9/201803/wpic014.jpg","春节放假通知","2019-1-12","春节放假10天"));
        mList.add(new Notice("http://pic.sc.chinaz.com/files/pic/pic9/201803/wpic008.jpg","春节放假通知","2019-1-12","春节放假10天"));

        noticeAdapter = new NoticeAdapter(NoticeActivity.this,mList);
        notice_lv.setAdapter(noticeAdapter);
        notice_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
