package com.autionsy.seller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.entity.News;
import com.autionsy.seller.entity.Notice;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeAdapter extends RecyclerView.Adapter {

    private Context context;
    List<Notice> noticesList = new ArrayList<>();

    public NoticeAdapter(Context context,List<Notice> list){
        this.context = context;
        this.noticesList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notice, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Notice notice = noticesList.get(i);

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.empty_image)
                .error(R.mipmap.empty_image);
        Glide.with(context)
                .load(noticesList.get(i).getHeader())
                .apply(options)
                .into(holder.notice_header_iv);
        holder.notice_title.setText(notice.getTitle());
        holder.notice_publish_time.setText(notice.getContent());
        holder.notice_content.setText(notice.getTime());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.notice_header_iv)
        ImageView notice_header_iv;
        @BindView(R.id.notice_title)
        TextView notice_title;
        @BindView(R.id.notice_publish_time)
        TextView notice_publish_time;
        @BindView(R.id.notice_content)
        TextView notice_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
