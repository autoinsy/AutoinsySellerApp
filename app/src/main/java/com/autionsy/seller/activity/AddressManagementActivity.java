package com.autionsy.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.AddressManagementAdapter;
import com.autionsy.seller.entity.Address;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressManagementActivity extends BaseActivity {

    @BindView(R.id.address_management_lv)
    ListView address_management_lv;

    @BindView(R.id.title_tv)
    TextView title_tv;

    private AddressManagementAdapter mAdapter;
    private List<Address> mList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_address_management);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){

        title_tv.setText(R.string.address_management);

        mAdapter = new AddressManagementAdapter(AddressManagementActivity.this,mList);
        address_management_lv.setAdapter(mAdapter);
        address_management_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(AddressManagementActivity.this,"点击了",Toast.LENGTH_SHORT).show();
                mAdapter.setSelectItem(position);
                mAdapter.notifyDataSetInvalidated();
            }
        });
    }

    @OnClick({R.id.back_btn,R.id.add_address_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.add_address_btn:
                Intent intent = new Intent(AddressManagementActivity.this,AddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
