package com.vijay.controllers.cart;

import com.vijay.exception.InvalidRequestException;
import com.vijay.requests.AddCartItemRequest;
import com.vijay.responses.GenericResponse;
import com.vijay.responses.Restaurant;
import com.vijay.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping(value = "/cart")
    public ResponseEntity<GenericResponse<Restaurant>> getCartItem(@RequestAttribute("userId") String userId) {

        return new ResponseEntity(cartService.getCart(Long.parseLong(userId)), HttpStatus.OK);
    }

    @PostMapping(value = "/cart")
    public ResponseEntity<GenericResponse<Restaurant>> addCartItem(@RequestAttribute("userId") String userId, @RequestBody AddCartItemRequest addCartItemRequest, BindingResult bindingResult) throws InvalidRequestException {

        if (bindingResult.hasErrors())
            throw new InvalidRequestException(bindingResult);

        return new ResponseEntity(cartService.addItemToCart(Long.parseLong(userId), addCartItemRequest), HttpStatus.OK);
    }
}
