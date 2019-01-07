package com.autionsy.seller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.autionsy.seller.R;

import butterknife.ButterKnife;

public class MineManagementAdapter extends BaseAdapter {

    private Context context;
    public int[] img_text ={R.string.goods_management_text, R.string.ornament_management_text,R.string.service_management_text,R.string.lease_management_text};
    public int[] images = { R.mipmap.product_manager, R.mipmap.mine_ornament,R.mipmap.mine_serivce,R.mipmap.mine_lease,R.mipmap.mine_recuit,R.mipmap.my_datum,R.mipmap.address_management};

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public class ViewHolder{

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
