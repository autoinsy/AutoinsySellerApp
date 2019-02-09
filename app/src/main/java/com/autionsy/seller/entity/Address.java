package com.autionsy.seller.entity;

import java.io.Serializable;

public class Address implements Serializable {
    private String receiverName;
    private String mobilePhoneNum;
    private String address;
    private String isDefault;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMobilePhoneNum() {
        return mobilePhoneNum;
    }

    public void setMobilePhoneNum(String mobilePhoneNum) {
        this.mobilePhoneNum = mobilePhoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}
