package com.example.madcamp_week2.server;

import com.google.gson.annotations.SerializedName;

public class RestResult {
    private String name;

    @SerializedName("contact")
    private String contact;

    private float rate;
    private int rateNum;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getRateNum() {
        return rateNum;
    }

    public void setRateNum(int rateNum) {
        this.rateNum = rateNum;
    }
}
