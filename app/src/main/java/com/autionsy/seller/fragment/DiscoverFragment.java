package com.autionsy.seller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.DiscoverListAdapter;
import com.autionsy.seller.entity.Discover;
import com.autionsy.seller.entity.DiscoverImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiscoverFragment extends BaseFragment {
    private View view;

    private DiscoverListAdapter discoverAdapter;
    private ArrayList<DiscoverImage> imageList = new ArrayList<>();
    private ArrayList<Discover> discoverList = new ArrayList<>();

    @BindView(R.id.discover_lv)
    ListView discover_lv;

    @BindView(R.id.add_blog_tv)
    TextView add_blog_tv;
    @BindView(R.id.title_tv)
    TextView title_tv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.frag_discover, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        initData();
        initView();
        return view;
    }

    private void initData(){

        for(int i=0;i<3; i++){
            Discover discover = new Discover();
            discover.setHeaderUrl("http://pic.sc.chinaz.com/files/pic/pic9/201606/apic21587.jpg");
            discover.setSellerName("美女艳照");
            discover.setPublishTime("2018-12-12");

            DiscoverImage discoverImage = new DiscoverImage();
            discoverImage.setImage("http://pic.sc.chinaz.com/files/pic/pic9/201608/apic22369.jpg");
            imageList.add(discoverImage);

            discover.setImageList(imageList);
            discoverList.add(discover);
        }
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.circle);
        add_blog_tv.setVisibility(View.VISIBLE);

        discoverAdapter = new DiscoverListAdapter(getActivity(),discoverList);
        discover_lv.setAdapter(discoverAdapter);
    }

    @OnClick({R.id.discover_search_layout,R.id.add_blog_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.discover_search_layout:

                break;
            case R.id.add_blog_tv:

                break;
        }
    }
}
