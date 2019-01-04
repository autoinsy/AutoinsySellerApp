package com.autionsy.seller.entity;

import java.io.Serializable;

public class News implements Serializable {
    private String imageUrl;
    private String title;
    private String content;
    private String date;

    public News(String url,String title,String content,String date){
        this.imageUrl = url;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
