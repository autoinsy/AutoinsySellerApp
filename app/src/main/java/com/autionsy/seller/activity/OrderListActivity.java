package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.OrderListAdapter;
import com.autionsy.seller.entity.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderListActivity extends BaseActivity {
    @BindView(R.id.title_tv)
    TextView title_tv;

    @BindView(R.id.trade_flow_lv)
    ListView trade_flow_lv;

    private OrderListAdapter mAdapter;
    private List<Order> mList = new ArrayList<>();

    private String orderState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_order_list);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);

        // 首先获取到意图对象
        Intent intent = getIntent();

        orderState = intent.getStringExtra("order_state");
        switch (orderState){
            case "0": /**订单状态为0,查看全部订单*/
                title_tv.setText(R.string.all_order);
                break;
            case "1": /**订单状态为1,待发货*/
                title_tv.setText(R.string.send_goods_title);
                break;
            case "2": /**订单状态为2，待收货*/
                title_tv.setText(R.string.receive_goods_title);
                break;
            case "3":  /**订单状态为3，待评价*/
                title_tv.setText(R.string.appraise_title);
                break;
            case "4": /**订单状态为4，退货/退款*/
                title_tv.setText(R.string.refund_title);
                break;
        }

        /**需要根据状态来发送请求*/
        mAdapter = new OrderListAdapter(OrderListActivity.this,mList);
        trade_flow_lv.setAdapter(mAdapter);
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
