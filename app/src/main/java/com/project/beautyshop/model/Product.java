package com.project.beautyshop.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    public int title;
    public String name;
    public int price;
    public int rawPrice;
    int quantity;
    int image;
    int mainImage;
    int shortDescription;
    int longDescription;
    boolean select;
    boolean bought;

    public Product(int mainImage, int price, int image, int title, String name, int shortDescription, int longDescription) {
//        this.id = UUID.randomUUID();
        this.mainImage = mainImage;
        this.price = price;
        this.image = image;
        this.title = title;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainImage() {
        return mainImage;
    }

    public void setMainImage(int mainImage) {
        this.mainImage = mainImage;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRawPrice() {
        return rawPrice;
    }

    public void setRawPrice(int discountPrice) {
        this.rawPrice = discountPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(int shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(int longDescription) {
        this.longDescription = longDescription;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}