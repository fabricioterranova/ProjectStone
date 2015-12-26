package com.fabriciooliveira.projectstone.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fabriciooliveira on 12/25/15.
 */
public class Product implements Parcelable {

    private String title;

    private int quantity;

    private String category;

    private float value;

    private int idImagem;

    public Product() {

    }

    public Product(String title, int quantity, String category, float value, int idImagem) {
        this.title = title;
        this.quantity = quantity;
        this.category = category;
        this.value = value;
        this.idImagem = idImagem;
    }

    public Product(Parcel input) {
        this.title = input.readString();
        this.quantity = input.readInt();
        this.category = input.readString();
        this.value = input.readFloat();
        this.idImagem = input.readInt();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.quantity);
        dest.writeString(this.category);
        dest.writeFloat(this.value);
        dest.writeInt(this.idImagem);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {

        public Product createFromParcel(Parcel in) {

            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

}
