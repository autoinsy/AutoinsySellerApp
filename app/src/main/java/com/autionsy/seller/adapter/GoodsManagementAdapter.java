package com.autionsy.seller.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.entity.Goods;
import com.autionsy.seller.utils.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsManagementAdapter extends BaseAdapter {

    private Context context;
    List<Goods> commodityList = new ArrayList<>();

    public GoodsManagementAdapter(Context context, List<Goods> list){
        this.context = context;
        this.commodityList = list;
    }

    @Override
    public int getCount() {
        return commodityList.size();
    }

    @Override
    public Object getItem(int position) {
        return commodityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_commodity_management, null);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.default_header)
//                .override(300, 300)
//                .error(R.mipmap.default_header);
//        Glide.with(context)
//                .load(commodityList.get(position).getCommodityImage())
//                .apply(options)
//                .into(holder.commodity_management_iv);
//
//        holder.commodity_management_title_tv.setText(commodityList.get(position).getTitle());
//        holder.commodity_unit_price.setText(commodityList.get(position).getUnitPrice());
//        holder.commodity_unit_quantity.setText(commodityList.get(position).getUnitQuantity());

        holder.commodity_management_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.commodity_management_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return convertView;
    }

    //初始化并弹出对话框方法
    private void showDialog(){
        View view = LayoutInflater.from(context).inflate(R.layout.dailog_delete_commodity,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(context).setView(view).create();

        Button btn_cancel_high_opion = view.findViewById(R.id.btn_cancel_high_opion);
        Button btn_agree_high_opion = view.findViewById(R.id.btn_agree_high_opion);

        btn_cancel_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_agree_high_opion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**发送请求删除item*/
                dialog.dismiss();
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(context)/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public class ViewHolder{

        @BindView(R.id.commodity_management_iv)
        ImageView commodity_management_iv;
        @BindView(R.id.commodity_management_title_tv)
        TextView commodity_management_title_tv;
        @BindView(R.id.commodity_unit_price)
        TextView commodity_unit_price;
        @BindView(R.id.commodity_unit_quantity)
        TextView commodity_unit_quantity;
        @BindView(R.id.commodity_management_edit_btn)
        Button commodity_management_edit_btn;
        @BindView(R.id.commodity_management_delete_btn)
        Button commodity_management_delete_btn;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
