package com.example.lab_1_2_sravansriramoju_c0828149_android.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {
    private int productId;
    private String productName;
    private double price;
    private double latitude;
    private double longitude;
    private String description;

    public ProductModel(int productId, String productName, double price, double latitude, double longitude, String description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public ProductModel(){

    }

    protected ProductModel(Parcel in) {
        productId = in.readInt();
        productName = in.readString();
        price = in.readDouble();
        latitude = in.readDouble();
        longitude = in.readDouble();
        description = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(productId);
        parcel.writeString(productName);
        parcel.writeDouble(price);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(description);
    }
}
