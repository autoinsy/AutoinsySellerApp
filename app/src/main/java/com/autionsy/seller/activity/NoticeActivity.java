package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.NoticeAdapter;
import com.autionsy.seller.constant.Constant;
import com.autionsy.seller.entity.Lease;
import com.autionsy.seller.entity.Notice;
import com.autionsy.seller.utils.OkHttp3Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NoticeActivity extends BaseActivity {

    @BindView(R.id.notice_lv)
    ListView notice_lv;
    @BindView(R.id.title_tv)
    TextView title_tv;

    private NoticeAdapter noticeAdapter;
    private List<Notice> mList = new ArrayList<>();
    private String noticeId;
    private Notice notice;

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

        postAsynHttpNotice();
    }

    @OnClick({R.id.back_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }

    private void postAsynHttpNotice(){
        notice = new Notice();

        String url = Constant.HTTP_URL + "noticeAll";
        Map<String,String> map = new HashMap<>();

        OkHttp3Utils.doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responeString = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(responeString);
                            String resultCode = jsonObject.optString("code");
                            String data = jsonObject.optString("data");
                            String message = jsonObject.optString("message");

                            if("200".equals(resultCode)){

                                JSONArray jsonArray = jsonObject.getJSONArray(data);
                                for (int i=0; i<jsonArray.length(); i++){
                                    JSONObject jsonObjectNotice = jsonArray.getJSONObject(i);
                                    notice.setContent(jsonObjectNotice.getString("noticeContent"));
                                    notice.setHeader(jsonObjectNotice.getString("noticePhoto"));
                                    notice.setTime(jsonObjectNotice.getString("noticeTime"));
                                    notice.setTitle(jsonObjectNotice.getString("noticeTitle"));
                                    mList.add(notice);
                                }

                                noticeAdapter = new NoticeAdapter(NoticeActivity.this,mList);
                                notice_lv.setAdapter(noticeAdapter);
                                notice_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        // 实例化一个Bundle
                                        Bundle bundle = new Bundle();
                                        Intent intent = new Intent(NoticeActivity.this,NoticeDetailActivity.class);
                                        bundle.putString("notice_id",noticeId);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });

                            }else if("403".equals(resultCode)){
                                Toast.makeText(getApplicationContext(),R.string.param_error,Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplicationContext(),R.string.login_fail,Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
