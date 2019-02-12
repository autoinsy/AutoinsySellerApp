package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.CommodityManagementAdapter;
import com.autionsy.seller.entity.Commodity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommodityManagementActivity extends BaseActivity {
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.commodity_management_lv)
    ListView commodity_management_lv;

    private CommodityManagementAdapter mAdapter;
    private List<Commodity> mList = new ArrayList<>();
    private String commodityManagementState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_commodity_management);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){

        title_tv.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        commodityManagementState = intent.getStringExtra("commodity_management_state");

        switch (commodityManagementState){
            case "1": //1代表汽配商品管理
                title_tv.setText(R.string.goods_management);
                break;
            case "2": //2代表内饰商品管理
                title_tv.setText(R.string.ornament_management);
                break;
            case "3": //3代表服务管理
                title_tv.setText(R.string.service_management);
                break;
            case "4": //4代表租赁管理
                title_tv.setText(R.string.lease_management);
                break;
            case "5": //5代表招聘管理
                title_tv.setText(R.string.recuit_management);
                break;
            case "6": //6代表道路救援管理
                title_tv.setText(R.string.rescue_management);
                break;
        }

        /**需要根据状态来发送请求*/
        mAdapter = new CommodityManagementAdapter(CommodityManagementActivity.this,mList);
        commodity_management_lv.setAdapter(mAdapter);
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
