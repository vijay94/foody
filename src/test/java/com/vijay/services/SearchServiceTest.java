package com.vijay.services;

import com.google.gson.Gson;
import com.vijay.responses.GenericResponse;
import com.vijay.service.discovery.SearchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceTest.class);

    @Autowired
    SearchService searchService;

    @Test
    public void search() {
        GenericResponse response = this.searchService.getRestaurants("Curry");
        LOGGER.info("Response Got => {}", new Gson().toJson(response));
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
