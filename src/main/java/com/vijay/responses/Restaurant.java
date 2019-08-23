package com.vijay.responses;

import com.google.gson.Gson;
import com.vijay.entities.Restaurants;
import com.vijay.entities.Reviews;

import java.util.List;

public class Restaurant {

    private String name;

    private long id;

    private List<Reviews> reviews;

    private String couverImage;

    private float average;

    public Restaurant(Restaurants restaurant, Double average) {
        this.name = restaurant.getRestaurantName();
        this.id = restaurant.getId();
        this.reviews = restaurant.getReviews();
        this.couverImage = restaurant.getCoverImage();
        this.average = average.floatValue();
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

    public String getCouverImage() {
        return couverImage;
    }

    public void setCouverImage(String couverImage) {
        this.couverImage = couverImage;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
