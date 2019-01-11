package com.autionsy.seller.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String headerImage;
    private String username;

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
