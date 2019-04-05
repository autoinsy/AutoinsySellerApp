package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.CommodityManagementAdapter;
import com.autionsy.seller.adapter.OrderListAdapter;
import com.autionsy.seller.constant.Constant;
import com.autionsy.seller.entity.Commodity;
import com.autionsy.seller.entity.Goods;
import com.autionsy.seller.entity.Lease;
import com.autionsy.seller.entity.Order;
import com.autionsy.seller.entity.Ornament;
import com.autionsy.seller.entity.Recruit;
import com.autionsy.seller.entity.Rescue;
import com.autionsy.seller.entity.Service;
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

public class CommodityManagementActivity extends BaseActivity {
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.commodity_management_lv)
    ListView commodity_management_lv;

    private CommodityManagementAdapter mAdapter;
    private List<Commodity> mList = new ArrayList<>();
    private String commodityManagementState;

    private Goods goods;
    private Lease lease;
    private Ornament ornament;
    private Recruit recruit;
    private Rescue rescue;
    private Service service;

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
                postAsynHttpGoods();
                break;
            case "2": //2代表内饰商品管理
                title_tv.setText(R.string.ornament_management);
                postAsynHttpOrnament();
                break;
            case "3": //3代表服务管理
                title_tv.setText(R.string.service_management);
                postAsynHttpService();
                break;
            case "4": //4代表租赁管理
                title_tv.setText(R.string.lease_management);
                postAsynHttpLease();
                break;
            case "5": //5代表招聘管理
                title_tv.setText(R.string.recuit_management);
                postAsynHttpRecruit();
                break;
            case "6": //6代表道路救援管理
                title_tv.setText(R.string.rescue_management);
                postAsynHttpRescue();
                break;
        }
    }

    @OnClick({R.id.back_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }

    /**商品*/
    private void postAsynHttpGoods(){
        goods = new Goods();
        String url = Constant.HTTP_URL + "login";

        Map<String,String> map = new HashMap<>();
//        map.put("commodityManagementState",commodityManagementState);

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
                                mAdapter = new CommodityManagementAdapter(CommodityManagementActivity.this,mList);
                                commodity_management_lv.setAdapter(mAdapter);

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

    /**租赁*/
    private void postAsynHttpLease(){
        lease = new Lease();
        String url = Constant.HTTP_URL + "login";

        Map<String,String> map = new HashMap<>();
//        map.put("commodityManagementState",commodityManagementState);

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
                                mAdapter = new CommodityManagementAdapter(CommodityManagementActivity.this,mList);
                                commodity_management_lv.setAdapter(mAdapter);

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

    /**内饰*/
    private void postAsynHttpOrnament(){
        lease = new Lease();
        String url = Constant.HTTP_URL + "login";

        Map<String,String> map = new HashMap<>();
//        map.put("commodityManagementState",commodityManagementState);

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
                                mAdapter = new CommodityManagementAdapter(CommodityManagementActivity.this,mList);
                                commodity_management_lv.setAdapter(mAdapter);

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

    /**服务*/
    private void postAsynHttpService(){
        service = new Service();
        String url = Constant.HTTP_URL + "login";

        Map<String,String> map = new HashMap<>();
//        map.put("commodityManagementState",commodityManagementState);

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
                                mAdapter = new CommodityManagementAdapter(CommodityManagementActivity.this,mList);
                                commodity_management_lv.setAdapter(mAdapter);

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

    /**招聘*/
    private void postAsynHttpRescue(){
        rescue = new Rescue();
        String url = Constant.HTTP_URL + "login";

        Map<String,String> map = new HashMap<>();
//        map.put("commodityManagementState",commodityManagementState);

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
                                mAdapter = new CommodityManagementAdapter(CommodityManagementActivity.this,mList);
                                commodity_management_lv.setAdapter(mAdapter);

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

    /**道路救援*/
    private void postAsynHttpRecruit(){
        rescue = new Rescue();
        String url = Constant.HTTP_URL + "login";

        Map<String,String> map = new HashMap<>();
//        map.put("commodityManagementState",commodityManagementState);

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
                                mAdapter = new CommodityManagementAdapter(CommodityManagementActivity.this,mList);
                                commodity_management_lv.setAdapter(mAdapter);

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
