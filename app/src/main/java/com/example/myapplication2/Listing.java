package com.example.myapplication2;

public class Listing {

    private String address;
    private String price;
    private int id;
    private byte[] image;
    private String type;
    private String description;
    private int isFavourite;

    public Listing (){}

    public Listing( int id, String address, String price, byte[] image, String type, String description, int isFavourite) {
        this.address = address;
        this.price = price;
        this.id = id;
        this.image = image;
        this.isFavourite = isFavourite;
        this.type = type;
        this.description = description;
    }

    public Listing(int id, String address, String price, byte[] image) {
        this.address = address;
        this.price = price;
        this.id = id;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public int getFavourite() {
        return isFavourite;
    }

    public void setFavourite(int favourite) {
        isFavourite = favourite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
