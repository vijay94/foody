package com.vijay.responses;

import com.google.gson.Gson;
import com.vijay.entities.Restaurants;

public class Restaurant {

    private String name;

    private long id;

    private String cousine;

    private String couverImage;

    public Restaurant(Restaurants restaurant) {
        this.name = restaurant.getRestaurantName();
        this.id = restaurant.getId();
        this.cousine = restaurant.getCousine();
        this.couverImage = restaurant.getCoverImage();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCousine() {
        return cousine;
    }

    public void setCousine(String cousine) {
        this.cousine = cousine;
    }

    public String getCouverImage() {
        return couverImage;
    }

    public void setCouverImage(String couverImage) {
        this.couverImage = couverImage;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
