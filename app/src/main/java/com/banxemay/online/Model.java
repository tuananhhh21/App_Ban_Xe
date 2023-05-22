package com.banxemay.online;

public class Model {
    String mDrinkName;
    String mDrinkDetail;
    int mDrinkPhoto;
    private int price;

    public Model(String mDrinkName, int price, String mDrinkDetail, int mDrinkPhoto) {
        this.mDrinkName = mDrinkName;
        this.setPrice(price);
        this.mDrinkDetail = mDrinkDetail;
        this.mDrinkPhoto = mDrinkPhoto;
    }

    public String getmDrinkName() {
        return mDrinkName;
    }

    public String getmDrinkDetail() {
        return mDrinkDetail;
    }

    public int getmDrinkPhoto() {
        return mDrinkPhoto;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
