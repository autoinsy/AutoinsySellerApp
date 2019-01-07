package com.autionsy.seller.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.activity.ExpressDeliveryCatchOrderActivity;
import com.autionsy.seller.activity.NewsActivity;
import com.autionsy.seller.activity.OrderExpressDeliveryActivity;
import com.autionsy.seller.activity.PublishGoodsActivity;
import com.autionsy.seller.activity.PublishLeaseActivity;
import com.autionsy.seller.activity.PublishOrnamentActivity;
import com.autionsy.seller.activity.PublishRecruitActivity;
import com.autionsy.seller.activity.PublishRescueActivity;
import com.autionsy.seller.activity.PublishServiceActivity;
import com.autionsy.seller.adapter.HomeAdapter;
import com.autionsy.seller.entity.News;
import com.autionsy.seller.views.ListViewInScrollView;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements OnBannerListener{

    private View view;
    private Intent intent;
    private Banner banner;
    private ArrayList<String> imageUrlList = new ArrayList<>();
    private ArrayList<String> list_title = new ArrayList<>();

    @BindView(R.id.back_btn)
    LinearLayout back_btn;
    @BindView(R.id.scan_btn)
    ImageView scan_btn;
    @BindView(R.id.search_layout)
    LinearLayout search_layout;
    @BindView(R.id.notice_btn)
    ImageView notice_btn;
    @BindView(R.id.home_news_listview)
    ListViewInScrollView home_news_listview;

    private ArrayList<News> newsList = new ArrayList<>();
    private HomeAdapter homeAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {

        banner = view.findViewById(R.id.banner);

        imageUrlList.add("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg");
        imageUrlList.add("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12514.jpg");
        imageUrlList.add("http://pic.sc.chinaz.com/files/pic/pic9/201810/zzpic14773.jpg");
        imageUrlList.add("http://pic1.sc.chinaz.com/files/pic/pic9/201810/zzpic14623.jpg");

        list_title.add("我爱NBA");
        list_title.add("我爱科比布莱恩特");
        list_title.add("我爱NBA");
        list_title.add("我爱科比布莱恩特");

        newsList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));
        newsList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));
        newsList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));
        newsList.add(new News("http://pic.sc.chinaz.com/files/pic/pic9/201806/zzpic12608.jpg",
                "Okhttp3基本使用",
                "HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。OkHttp是一个高效的HTTP客户端，它有以下默认特性",
                "2018-12-18"));

        //简单使用
        banner.setImages(imageUrlList)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setImageLoader(new GlideImageLoader())
                .setBannerAnimation(Transformer.Default)
                .setBannerTitles(list_title)
                .setOnBannerListener(this)
                .start();

        back_btn.setVisibility(View.GONE);
        scan_btn.setVisibility(View.VISIBLE);
        search_layout.setVisibility(View.VISIBLE);
        notice_btn.setVisibility(View.VISIBLE);

        homeAdapter = new HomeAdapter(getActivity(),newsList);
        home_news_listview.setAdapter(homeAdapter);
        home_news_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    @OnClick({R.id.scan_btn,
            R.id.search_layout,
            R.id.notice_btn,
            R.id.publish_goods_layout,
            R.id.publish_service_layout,
            R.id.publish_rescue_layout,
            R.id.publish_lease_layout,
            R.id.publish_recruit_layout,
            R.id.publish_ornament_layout,
            R.id.order_delivery_layout,
            R.id.publish_catch_order_layout,
            R.id.more_news})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.scan_btn:

                break;
            case R.id.search_layout:

                break;
            case R.id.notice_btn:

                break;
            case R.id.publish_goods_layout:
                intent = new Intent(getActivity(),PublishGoodsActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_service_layout:
                intent = new Intent(getActivity(),PublishServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_rescue_layout:
                intent = new Intent(getActivity(),PublishRescueActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_lease_layout:
                intent = new Intent(getActivity(),PublishLeaseActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_recruit_layout:
                intent = new Intent(getActivity(),PublishRecruitActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_ornament_layout:
                intent = new Intent(getActivity(),PublishOrnamentActivity.class);
                startActivity(intent);
                break;
            case R.id.order_delivery_layout:
                intent = new Intent(getActivity(),OrderExpressDeliveryActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_catch_order_layout:
                intent = new Intent(getActivity(),ExpressDeliveryCatchOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.more_news:
                intent = new Intent(getActivity(),NewsActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 轮播监听
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }

    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }
}
