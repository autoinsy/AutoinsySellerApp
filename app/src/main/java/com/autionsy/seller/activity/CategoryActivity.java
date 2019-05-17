package com.autionsy.seller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.constant.Constants;
import com.autionsy.seller.utils.OkHttp3Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
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

    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_category);
        ButterKnife.bind(this);
        Fresco.initialize(this);
        initView();
        loadData();
    }

    private void loadData() {
        String url = Constants.HTTP_URL;

        Map<String,String> map = new HashMap<>();

        OkHttp3Utils.doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responeString = response.body().string();

                if(response.isSuccessful()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                org.json.JSONObject jsonObject = new org.json.JSONObject(responeString);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.category_title_text);


    }

    @OnClick({R.id.back_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(title_tv != null){
            title_tv = null;
        }

    }
}
