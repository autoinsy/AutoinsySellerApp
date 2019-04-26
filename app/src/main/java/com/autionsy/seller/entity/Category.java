package com.autionsy.seller.entity;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    private long mainClassifyId;
    private String mainClassify;
    private String mainClassifyCode;
    private List<CategoryData> categoryDataList;

    public long getMainClassifyId() {
        return mainClassifyId;
    }

    public void setMainClassifyId(long mainClassifyId) {
        this.mainClassifyId = mainClassifyId;
    }

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

    public List<CategoryData> getCategoryDataList() {
        return categoryDataList;
    }

    public void setCategoryDataList(List<CategoryData> categoryDataList) {
        this.categoryDataList = categoryDataList;
    }

    public static class CategoryData{
        private long subClassifyId;
        private String subClassify;
        private String subClassifyImage;

        public long getSubClassifyId() {
            return subClassifyId;
        }

        public void setSubClassifyId(long subClassifyId) {
            this.subClassifyId = subClassifyId;
        }

        public String getSubClassify() {
            return subClassify;
        }

        public void setSubClassify(String subClassify) {
            this.subClassify = subClassify;
        }

        public String getSubClassifyImage() {
            return subClassifyImage;
        }

        public void setSubClassifyImage(String subClassifyImage) {
            this.subClassifyImage = subClassifyImage;
        }
    }
}
