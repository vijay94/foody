package com.vijay.respositories;

import com.google.gson.Gson;
import com.vijay.entities.Restaurants;
import com.vijay.repositories.RestaurantsRepository;
import com.vijay.services.SearchServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantRepoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantRepoTest.class);

    @Autowired
    RestaurantsRepository restaurantsRepository;

    @Test
    public void fetchAllTest() {

        List<Restaurants> restaurants = this.restaurantsRepository.findAll();
        LOGGER.info("Restaurants Available => {}", new Gson().toJson(restaurants));
        Assert.assertNotNull(restaurants);

    }

}
