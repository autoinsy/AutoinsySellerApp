package com.autionsy.seller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.constant.Constants;
import com.autionsy.seller.entity.CategoryMain;
import com.autionsy.seller.entity.CategorySub;
import com.autionsy.seller.utils.ListViewUtils;
import com.autionsy.seller.utils.OkHttp3Utils;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
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

public class CategoryActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.sub_category_lv)
    ListView sub_category_lv;
    @BindView(R.id.main_category_lv)
    ListView main_category_lv;

    private List<CategoryMain> categoryMainList = new ArrayList<>();
    private List<CategorySub> categorySubList = new ArrayList<>();
    private CategoryMain categoryMain;
    private CategorySub categorySub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_category);
        ButterKnife.bind(this);
        Fresco.initialize(this);
        initView();
        loadData();
    }

    private void initView() {
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.category_title_text);

        main_category_lv.setSelection(0);
        sub_category_lv.setSelection(0);
    }

    private void loadData() {
        String url = Constants.HTTP_URL;

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
                            org.json.JSONObject jsonObject = new org.json.JSONObject(responeString);
                            String resultCode = jsonObject.optString("code");
                            String data = jsonObject.optString("data");
                            String message = jsonObject.optString("message");
                            if("200".equals(resultCode)){
                                JSONArray jsonArray = jsonObject.getJSONArray(data);
                                categoryMain = new CategoryMain();
                                categorySub = new CategorySub();
                                for (int i=0; i<jsonArray.length(); i++){
                                    JSONObject jsonObjectMainData = jsonArray.getJSONObject(i);
                                    categoryMain.setMainClassify(jsonObjectMainData.getString("mainClassify"));
                                    categoryMain.setMainClassifyCode(jsonObjectMainData.getString("mainClassifyCode"));


                                }
                            }else if("410".equals(resultCode)){
                                Toast.makeText(getApplicationContext(),"没有数据",Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
        if (title_tv != null) {
            title_tv = null;
        }

    }
}
