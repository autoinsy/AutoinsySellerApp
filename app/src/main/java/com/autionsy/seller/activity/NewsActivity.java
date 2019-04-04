package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.NewsAdapter;
import com.autionsy.seller.constant.Constant;
import com.autionsy.seller.entity.Lease;
import com.autionsy.seller.entity.News;
import com.autionsy.seller.utils.OkHttp3Utils;
import com.autionsy.seller.views.RecyclerViewDivider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsActivity extends BaseActivity {

    @BindView(R.id.news_recycler_view)
    RecyclerView news_recycler_view;
    @BindView(R.id.title_tv)
    TextView title_tv;

    private NewsAdapter newsAdapter;

    private ArrayList<News> newsArrayList = new ArrayList<>();

    private News news;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){

        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.news_title_text);

        postAsynHttpGoods();
    }

    @OnClick({R.id.back_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }

    private void postAsynHttpGoods(){
        news = new News();

        String url = Constant.HTTP_URL + "login";

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

                                LinearLayoutManager manager=new LinearLayoutManager(this);
                                news_recycler_view.setLayoutManager(manager);
                                news_recycler_view.addItemDecoration(new RecyclerViewDivider(NewsActivity.this, LinearLayoutManager.HORIZONTAL, 2, ContextCompat.getColor(NewsActivity.this, R.color.gray_line_2)));
                                newsAdapter = new NewsAdapter(NewsActivity.this,newsArrayList);
                                news_recycler_view.setAdapter(newsAdapter);
                                newsAdapter.setOnItemClickListener(new NewsAdapter.OnRecyclerViewItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, News news) {
                                        Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("note", news);
                                        intent.putExtra("data", bundle);
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
