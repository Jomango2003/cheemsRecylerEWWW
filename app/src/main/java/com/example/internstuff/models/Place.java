package com.example.internstuff.models;

public class Place {
    private String title;
    private String imageUrl;

    public Place(String title, String image){
        this.title = title;
        this.imageUrl = image;
    }

    public String getTitle(){ return title; }

    public void setTitle(String title) { this.title = title; }

    public String getImage(){ return imageUrl; }

    public void setImage(String image) { this.imageUrl = image; }

}
