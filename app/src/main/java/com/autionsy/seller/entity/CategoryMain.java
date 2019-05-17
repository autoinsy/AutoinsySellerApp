package com.autionsy.seller.entity;

import java.io.Serializable;
import java.util.List;

public class CategoryMain implements Serializable {

    private String mainClassify;
    private String mainClassifyCode;
    private String mainClassifyId;
    private String mainClassifyImageUrl;
    private List<CategorySub> dataList;

    public String getMainClassify() {
        return mainClassify;
    }

    public void setMainClassify(String mainClassify) {
        this.mainClassify = mainClassify;
    }

    public String getMainClassifyCode() {
        return mainClassifyCode;
    }

    public void setMainClassifyCode(String mainClassifyCode) {
        this.mainClassifyCode = mainClassifyCode;
    }

    public String getMainClassifyId() {
        return mainClassifyId;
    }

    public void setMainClassifyId(String mainClassifyId) {
        this.mainClassifyId = mainClassifyId;
    }

    public String getMainClassifyImageUrl() {
        return mainClassifyImageUrl;
    }

    public void setMainClassifyImageUrl(String mainClassifyImageUrl) {
        this.mainClassifyImageUrl = mainClassifyImageUrl;
    }

    public List<CategorySub> getDataList() {
        return dataList;
    }

    public void setDataList(List<CategorySub> dataList) {
        this.dataList = dataList;
    }
}
