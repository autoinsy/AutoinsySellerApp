package com.autionsy.seller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.autionsy.seller.R;
import com.autionsy.seller.entity.News;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter {

    private Context context;
    ArrayList<News> newsList = new ArrayList<>();

    public NewsAdapter(Context context,ArrayList<News> list){
        this.context = context;
        this.newsList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        News news = newsList.get(i);

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.empty_image)
                .error(R.mipmap.empty_image);
        Glide.with(context)
                .load(newsList.get(i).getImageUrl())
                .apply(options)
                .into(holder.news_iv);
        holder.news_title_tv.setText(news.getTitle());
        holder.news_content_tv.setText(news.getContent());
        holder.news_publish_date.setText(news.getDate());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.news_iv)
        ImageView news_iv;
        @BindView(R.id.news_title_tv)
        TextView news_title_tv;
        @BindView(R.id.news_content_tv)
        TextView news_content_tv;
        @BindView(R.id.news_publish_date)
        TextView news_publish_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
