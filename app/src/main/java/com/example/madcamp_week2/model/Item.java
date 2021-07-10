package com.example.madcamp_week2.model;

import java.util.ArrayList;

public class Item {

    // common
    public int type;
    public String category;

    // if header
    public ArrayList<Item> invisibleChildren;

    // if child
    public Dictionary dict;
    public double rate;
    public int rateNum;

    public Item(String category){
        this.category = category;
        this.type = 0;
    }
    public Item(String category, Dictionary dict) {
        this.dict = new Dictionary(dict.getName(), dict.getContact());
        this.type = 1;
        this.category = category;
    }


    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item i = (Item) obj;

            if (this.dict != null && i.dict != null)
                return i.dict.getName().equals(this.dict.getName());
        }
        return false;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<Item> getInvisibleChildren() {
        return invisibleChildren;
    }

    public void setInvisibleChildren(ArrayList<Item> invisibleChildren) {
        this.invisibleChildren = invisibleChildren;
    }

    public Dictionary getDict() {
        return dict;
    }

    public void setDict(Dictionary dict) {
        this.dict = dict;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getRateNum() {
        return rateNum;
    }

    public void setRateNum(int rateNum) {
        this.rateNum = rateNum;
    }
}
