package com.vijay.responses;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {

    private List<Restaurant> restaurants;

    public SearchResponse() {
        this.restaurants = new ArrayList<>();
    }

    public SearchResponse(List<Restaurant> restaurants) {
        this();
        this.restaurants = restaurants;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
