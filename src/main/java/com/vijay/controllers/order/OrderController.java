package com.vijay.controllers.order;

import com.vijay.requests.PlaceOrderRequest;
import com.vijay.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/order")
    public ResponseEntity placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest) {

        return new ResponseEntity(orderService.placeOrder(placeOrderRequest), HttpStatus.OK);
    }
}
