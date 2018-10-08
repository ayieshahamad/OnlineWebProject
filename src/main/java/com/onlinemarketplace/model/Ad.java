package com.onlinemarketplace.model;

public class Ad {
    int userID;
    int productID;
    String name;
    String type;
    double price;
    String description;
    String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //String diliveryOptions;
    boolean available;
    //String payment;
    String datePosted;
    String imageUrl;
    String imageUrl2;

    public Ad() {
        productID = -1;
        name = null;
        type = null;
        price = 0;
        description = null;
        available = false;
        datePosted = null;
        imageUrl = null;
        imageUrl2 = null;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Ad(int productID, String name, String type, double price, String description, boolean available, String datePosted, String imageUrl, String imageUrl2) {
        this.productID = productID;
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
        this.available = available;
        this.datePosted = datePosted;
        this.imageUrl = imageUrl;
        this.imageUrl2 = imageUrl2;
    }

    public void createAd(){
        productID = -1;
        name = "My Ad";
        type = "test";
        price = 50;
        description = "description of product can we very long so that it does not displays completely.\n but yo can view it by clicking the image.";
        available = true;
        datePosted = null;
        imageUrl = "D:\\ComIT.ORG\\imageURL\\1.jpg";
        imageUrl2 = "D:\\ComIT.ORG\\imageURL\\2.jpg";
    }
    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }
}
