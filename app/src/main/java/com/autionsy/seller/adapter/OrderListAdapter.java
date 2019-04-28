package com.autionsy.seller.adapter;

import android.annotation.SuppressLint;
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
import com.autionsy.seller.entity.Order;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListAdapter extends BaseAdapter {

    private Context context;
    List<Order> tradeFlowList = new ArrayList<>();

    public OrderListAdapter(Context context, List<Order> list){
        this.context = context;
        this.tradeFlowList = list;
    }

    @Override
    public int getCount() {
        return tradeFlowList.size();
    }

    @Override
    public Object getItem(int position) {
        return tradeFlowList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_list, null);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.default_header)
                .override(300, 300)
                .error(R.mipmap.default_header);
        Glide.with(context)
                .load(tradeFlowList.get(position).getPictureUrl())
                .apply(options)
                .into(holder.trade_flow_goods_iv);

        holder.trade_flow_goods_title_tv.setText(tradeFlowList.get(position).getGoodsTitle());
        holder.trade_flow_goods_price.setText("¥"+tradeFlowList.get(position).getPrice());
        holder.trade_flow_goods_quantity.setText("x"+tradeFlowList.get(position).getQuantity());
        holder.trade_flow_actual_quantity.setText("共"+tradeFlowList.get(position).getQuantity());
        holder.trade_flow_total.setText("¥"+tradeFlowList.get(position).getTotal());

//        String orderState = tradeFlowList.get(position).getOrderState();
//        switch (orderState){
//            case "0": /**订单状态为0,查看全部订单*/
//                holder.trade_flow_delete_order_layout.setVisibility(View.VISIBLE);
//                holder.trade_flow_delete_order_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                break;
//            case "1": /**订单状态为1,待发货*/
//                holder.trade_flow_send_goods_layout.setVisibility(View.VISIBLE);
//                holder.trade_flow_cancel_order_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                holder.trade_flow_send_goods_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                break;
//            case "2":  /**订单状态为2，待收货*/
//                holder.trade_flow_receive_goods_layout.setVisibility(View.VISIBLE);
//                holder.trade_flow_check_delivery_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                holder.trade_flow_sign_receive_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                break;
//            case "3": /**订单状态为3，待评价*/
//                holder.trade_flow_appraise_layout.setVisibility(View.VISIBLE);
//                holder.trade_flow_appraise_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                break;
//            case "4": /**订单状态为4，退货/退款*/
//                holder.trade_flow_refund_layout.setVisibility(View.VISIBLE);
//                holder.trade_flow_refund_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                break;
//        }

        return convertView;
    }

    public class ViewHolder{

        @BindView(R.id.trade_flow_goods_iv)
        ImageView trade_flow_goods_iv;
        @BindView(R.id.trade_flow_goods_title_tv)
        TextView trade_flow_goods_title_tv;
        @BindView(R.id.trade_flow_goods_price)
        TextView trade_flow_goods_price;
        @BindView(R.id.trade_flow_goods_quantity)
        TextView trade_flow_goods_quantity;
        @BindView(R.id.trade_flow_actual_quantity)
        TextView trade_flow_actual_quantity;
        @BindView(R.id.trade_flow_total)
        TextView trade_flow_total;

//        /**订单状态为0,查看全部订单*/
//        @BindView(R.id.trade_flow_delete_order_layout)
//        LinearLayout trade_flow_delete_order_layout;
//        @BindView(R.id.trade_flow_delete_order_btn)
//        Button trade_flow_delete_order_btn;
//
//        /**订单状态为1,待发货*/
//        @BindView(R.id.trade_flow_send_goods_layout)
//        LinearLayout trade_flow_send_goods_layout;
//        @BindView(R.id.trade_flow_cancel_order_btn)
//        Button trade_flow_cancel_order_btn;
//        @BindView(R.id.trade_flow_send_goods_btn)
//        Button trade_flow_send_goods_btn;
//
//        /**订单状态为2，待收货*/
//        @BindView(R.id.trade_flow_receive_goods_layout)
//        LinearLayout trade_flow_receive_goods_layout;
//        @BindView(R.id.trade_flow_check_delivery_btn)
//        Button trade_flow_check_delivery_btn;
//        @BindView(R.id.trade_flow_sign_receive_btn)
//        Button trade_flow_sign_receive_btn;
//
//        /**订单状态为3，待评价*/
//        @BindView(R.id.trade_flow_appraise_layout)
//        LinearLayout trade_flow_appraise_layout;
//        @BindView(R.id.trade_flow_appraise_btn)
//        Button trade_flow_appraise_btn;
//
//        /**订单状态为4，退货/退款*/
//        @BindView(R.id.trade_flow_refund_layout)
//        LinearLayout trade_flow_refund_layout;
//        @BindView(R.id.trade_flow_refund_btn)
//        Button trade_flow_refund_btn;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
