package com.vijay.service;

import com.vijay.repositories.RestaurantsRepository;
import com.vijay.responses.GenericResponse;
import com.vijay.responses.Restaurant;
import com.vijay.responses.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    RestaurantsRepository restaurantsRepository;

    public GenericResponse getRestaurants(String searchTerm) {
        LOGGER.info("Search Term is => {}", searchTerm);

        StringBuilder stringBuilder = new StringBuilder("%").append(searchTerm).append("%");
        List<Restaurant> restaurants = this.restaurantsRepository.search(stringBuilder.toString());
        return new GenericResponse(200, new SearchResponse(restaurants), "Success");
    }
}
