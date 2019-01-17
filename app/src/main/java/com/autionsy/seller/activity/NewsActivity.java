package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.NewsAdapter;
import com.autionsy.seller.entity.News;
import com.autionsy.seller.views.RecyclerViewDivider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity {

    @BindView(R.id.news_recycler_view)
    RecyclerView news_recycler_view;
    @BindView(R.id.title_tv)
    TextView title_tv;

    private NewsAdapter newsAdapter;

    private ArrayList<News> newsArrayList = new ArrayList<>();

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

        newsArrayList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));
        newsArrayList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));
        newsArrayList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));
        newsArrayList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));
        newsArrayList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));
        newsArrayList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));
        newsArrayList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));
        newsArrayList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));

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
