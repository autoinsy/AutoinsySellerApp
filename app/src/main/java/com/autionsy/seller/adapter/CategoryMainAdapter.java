package com.autionsy.seller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.entity.CategoryMain;
import com.autionsy.seller.entity.CategorySub;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryMainAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryMain> mList;
    private int selectedPosition = 0;

    public CategoryMainAdapter(Context mContext, List<CategoryMain> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_main_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //选中和没选中时，设置不同的颜色
        if (position == selectedPosition){
            convertView.setBackgroundResource(R.color.bg_gray);
        }else{
            convertView.setBackgroundResource(R.color.orange_title_bg);
        }

        holder.main_category_lv.setText(mList.get(position).getMainClassify());

        return convertView;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public class ViewHolder{
        @BindView(R.id.main_category_lv)
        TextView main_category_lv;
        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
