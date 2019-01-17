package com.autionsy.seller.entity;

import java.io.Serializable;

public class Notice implements Serializable {
    private String header;
    private String title;
    private String time;
    private String content;

    public Notice(String header,String title,String time,String content){
        this.header = header;
        this.title = title;
        this.time = time;
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
