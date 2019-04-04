package com.autionsy.seller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.BrandAdapter;
import com.autionsy.seller.entity.Brand;
import com.scrat.app.selectorlibrary.ImageSelector;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrandActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.brand_gv)
    GridView brand_gv;

    private BrandAdapter brandAdapter;
    private List<Brand> mList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_publish_goods);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.publish_goods);

        brandAdapter = new BrandAdapter(BrandActivity.this,mList);
        brand_gv.setAdapter(brandAdapter);
        brand_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
