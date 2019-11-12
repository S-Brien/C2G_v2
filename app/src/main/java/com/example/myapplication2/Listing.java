package com.example.myapplication2;

public class Listing {

    private String address;
    private String price;
    private int id;

    public Listing (){}

    public Listing( int id, String address, String price) {
        this.address = address;
        this.price = price;
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
