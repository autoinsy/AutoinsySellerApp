package com.autionsy.seller.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.constant.Constants;
import com.autionsy.seller.entity.News;
import com.autionsy.seller.utils.OkHttp3Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Subscription;

public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.news_detail_title_tv)
    TextView news_detail_title_tv;
    @BindView(R.id.news_detail_time_tv)
    TextView news_detail_time_tv;

    @BindView(R.id.news_image_url_1)
    ImageView news_image_url_1;
    @BindView(R.id.news_content_tv)
    TextView news_content_tv;
    @BindView(R.id.news_image_url_2)
    ImageView news_image_url_2;

    private String myTitle;
    private String myGroupName;
    private News news;
    private ProgressDialog loadingDialog;
    private Subscription subsLoading;

    private String newsId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_detail);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.news_detail_text);

        newsId = getIntent().getStringExtra("newsId");

        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("数据加载中...");
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();

        postAsynHttpGoods();
    }

    private void postAsynHttpGoods() {
        news = new News();

        String url = Constants.HTTP_URL + "getNews";

        Map<String, String> map = new HashMap<>();
        map.put("news_id",newsId);

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

                            if ("200".equals(resultCode)) {

                                JSONObject jsonObjectNews = jsonObject.getJSONObject(data);
                                String title = jsonObjectNews.getString("newsTitle");
                                String content = jsonObjectNews.getString("content");
                                String pulishTime = jsonObjectNews.getString("publishTime");
                                String imageUrl1 = jsonObjectNews.getString("newsImageUrl1");
                                String imageUrl2 = jsonObjectNews.getString("newsImageUrl2");

                                news_detail_title_tv.setText(title);
                                news_detail_time_tv.setText(pulishTime);
                                news_content_tv.setText(content);

                                RequestOptions options = new RequestOptions()
                                        .placeholder(R.mipmap.empty_image)
                                        .error(R.mipmap.empty_image);
                                Glide.with(NewsDetailActivity.this)
                                        .load(imageUrl1)
                                        .apply(options)
                                        .into(news_image_url_1);

                                RequestOptions options1 = new RequestOptions()
                                        .placeholder(R.mipmap.empty_image)
                                        .error(R.mipmap.empty_image);
                                Glide.with(NewsDetailActivity.this)
                                        .load(imageUrl2)
                                        .apply(options1)
                                        .into(news_image_url_2);

                            } else if ("403".equals(resultCode)) {
                                Toast.makeText(getApplicationContext(), R.string.param_error, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), R.string.login_fail, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @OnClick({R.id.back_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
