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
import com.autionsy.seller.adapter.OrderListAdapter;
import com.autionsy.seller.constant.Constant;
import com.autionsy.seller.entity.Notice;
import com.autionsy.seller.entity.Order;
import com.autionsy.seller.utils.OkHttp3Utils;

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

public class OrderListActivity extends BaseActivity {
    @BindView(R.id.title_tv)
    TextView title_tv;

    @BindView(R.id.trade_flow_lv)
    ListView trade_flow_lv;

    private OrderListAdapter mAdapter;
    private List<Order> mList = new ArrayList<>();

    private String orderState;

    private Order order;

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
        postAsynHttpOrder();
    }

    @OnClick({R.id.back_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }

    private void postAsynHttpOrder(){

        order = new Order();

        String url = Constant.HTTP_URL + "login";

        Map<String,String> map = new HashMap<>();
        map.put("orderState",orderState);

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

                                /**需要根据状态来发送请求*/
                                mAdapter = new OrderListAdapter(OrderListActivity.this,mList);
                                trade_flow_lv.setAdapter(mAdapter);

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
