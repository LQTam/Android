package com.example.myapplication;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private int price;
    private int image;
    private boolean book;
    public Food(){}
    public Food(String name, int price, int image, boolean book) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.book = book;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isBook() {
        return book;
    }

    public void setBook(boolean book) {
        this.book = book;
    }
}
