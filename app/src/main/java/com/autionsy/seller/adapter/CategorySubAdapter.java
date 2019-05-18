package com.autionsy.seller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.entity.CategoryMain;
import com.autionsy.seller.entity.CategorySub;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategorySubAdapter extends BaseAdapter {

    private Context context;
    private List<CategorySub> mList;

    public CategorySubAdapter(Context mContext, List<CategorySub> list){
        this.context = mContext;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_sub_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sub_category_tv.setText(mList.get(position).getSubClassify());
        return convertView;
    }

    public class ViewHolder{
        @BindView(R.id.sub_category_tv)
        TextView sub_category_tv;
        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
