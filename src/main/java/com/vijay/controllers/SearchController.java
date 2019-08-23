package com.vijay.controllers;

import com.vijay.exception.InvalidRequestException;
import com.vijay.responses.GenericResponse;
import com.vijay.responses.Restaurant;
import com.vijay.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("restaurants")
    public ResponseEntity<GenericResponse<Restaurant>> getRestaurants(@RequestParam(name = "term", required = true) String searchTerm, BindingResult bindingResult) throws InvalidRequestException {

        if (bindingResult.hasErrors())
            throw new InvalidRequestException(bindingResult);

        return new ResponseEntity(searchService.getRestaurants(searchTerm), HttpStatus.OK);
    }
}