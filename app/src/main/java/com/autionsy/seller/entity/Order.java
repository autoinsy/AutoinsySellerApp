package com.autionsy.seller.entity;

import java.io.Serializable;

public class Order implements Serializable {

    private String goodsImage;
    private String goodsTitle;
    private String price;
    private String quantity;
    private String total;
    private String actualQuantity;
    private String orderState; // 0代表展示全部订单，1代表发货订单，2代表待签收订单，3代表去评价订单，4代表退货退款订单

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(String actualQuantity) {
        this.actualQuantity = actualQuantity;
    }
    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
