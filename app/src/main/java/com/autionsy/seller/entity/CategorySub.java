package com.autionsy.seller.entity;

import java.io.Serializable;

public class CategorySub implements Serializable {
    private String mainClassifyCode;
    private String subClassify;
    private String subClassifyId;
    private String subClassifyImage;

    public String getMainClassifyCode() {
        return mainClassifyCode;
    }

    public void setMainClassifyCode(String mainClassifyCode) {
        this.mainClassifyCode = mainClassifyCode;
    }

    public String getSubClassify() {
        return subClassify;
    }

    public void setSubClassify(String subClassify) {
        this.subClassify = subClassify;
    }

    public String getSubClassifyId() {
        return subClassifyId;
    }

    public void setSubClassifyId(String subClassifyId) {
        this.subClassifyId = subClassifyId;
    }

    public String getSubClassifyImage() {
        return subClassifyImage;
    }

    public void setSubClassifyImage(String subClassifyImage) {
        this.subClassifyImage = subClassifyImage;
    }
}
