package com.autionsy.seller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.NewsAdapter;
import com.autionsy.seller.constant.Constant;
import com.autionsy.seller.entity.News;
import com.autionsy.seller.utils.OkHttp3Utils;
import com.autionsy.seller.utils.StringUtils;
import com.autionsy.seller.views.RecyclerViewDivider;
import com.sendtion.xrichtext.RichTextView;

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
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.news_detail_title_tv)
    TextView news_detail_title_tv;
    @BindView(R.id.news_detail_time_tv)
    TextView news_detail_time_tv;
    @BindView(R.id.news_detail_content)
    com.sendtion.xrichtext.RichTextView news_detail_content;

    private String myTitle;
    private String myContent;
    private String myGroupName;
    private News news;
    private ProgressDialog loadingDialog;
    private Subscription subsLoading;

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

        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("数据加载中...");
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();

        postAsynHttpGoods();

        news_detail_content.post(new Runnable() {
            @Override
            public void run() {
                //showEditData(myContent);
                news_detail_content.clearAllLayout();
                showDataSync(myContent);
            }
        });
    }

    private void postAsynHttpGoods() {
        news = new News();

        String url = Constant.HTTP_URL + "login";

        Map<String, String> map = new HashMap<>();

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


                                // 图片点击事件
                                news_detail_content.setOnRtImageClickListener(new RichTextView.OnRtImageClickListener() {
                                    @Override
                                    public void onRtImageClick(String imagePath) {
                                        ArrayList<String> imageList = StringUtils.getTextFromHtml(myContent, true);
                                        int currentPosition = imageList.indexOf(imagePath);


                                        //点击图片预览
//                                        PhotoPreview.builder()
//                                                .setPhotos(imageList)
//                                                .setCurrentItem(currentPosition)
//                                                .setShowDeleteButton(false)
//                                                .start(NoteActivity.this);
                                    }
                                });

                                Intent intent = getIntent();
                                Bundle bundle = intent.getBundleExtra("data");

                                /**设置数据源*/
                                news = (News) bundle.getSerializable("note");
                                myContent = news.getContent();
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

    /**
     * 异步方式显示数据
     *
     * @param html
     */
    private void showDataSync(final String html) {

        subsLoading = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                showEditData(subscriber, html);
            }
        })
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())//生产事件在io
                .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                        }
                        showToast("解析错误：图片不存在或已损坏");
                    }

                    @Override
                    public void onNext(String text) {
                        if (text.contains("<img") && text.contains("src=")) {
                            //imagePath可能是本地路径，也可能是网络地址
                            String imagePath = StringUtils.getImgSrc(text);
                            news_detail_content.addImageViewAtIndex(news_detail_content.getLastIndex(), imagePath);
                        } else {
                            news_detail_content.addTextViewAtIndex(news_detail_content.getLastIndex(), text);
                        }
                    }
                });
    }

    /**
     * 显示数据
     *
     * @param html
     */
    private void showEditData(Subscriber<? super String> subscriber, String html) {
        try {
            List<String> textList = StringUtils.cutStringByImgTag(html);
            for (int i = 0; i < textList.size(); i++) {
                String text = textList.get(i);
                subscriber.onNext(text);
            }
            subscriber.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
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
