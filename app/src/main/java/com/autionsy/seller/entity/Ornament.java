package com.autionsy.seller.entity;

import java.io.Serializable;

public class Ornament implements Serializable {

    private String ornamentName;

    private String price;

    private String weight;

    private String motorcycleType;

    private String motorcycleFrameNumber;

    private String ornamentCode;

    private String brand;

    private String videoUrl;

    private String ornamentLoopCode;

    private String username;

    private String ornamentType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrnamentName() {
        return ornamentName;
    }

    public void setOrnamentName(String ornamentName) {
        this.ornamentName = ornamentName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    public String getMotorcycleFrameNumber() {
        return motorcycleFrameNumber;
    }

    public void setMotorcycleFrameNumber(String motorcycleFrameNumber) {
        this.motorcycleFrameNumber = motorcycleFrameNumber;
    }

    public String getOrnamentCode() {
        return ornamentCode;
    }

    public void setOrnamentCode(String ornamentCode) {
        this.ornamentCode = ornamentCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getOrnamentLoopCode() {
        return ornamentLoopCode;
    }

    public void setOrnamentLoopCode(String ornamentLoopCode) {
        this.ornamentLoopCode = ornamentLoopCode;
    }

    public String getOrnamentType() {
        return ornamentType;
    }

    public void setOrnamentType(String ornamentType) {
        this.ornamentType = ornamentType;
    }
}
