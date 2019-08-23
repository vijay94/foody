package com.vijay.service.order;

import com.vijay.entities.Addresses;
import com.vijay.entities.Cart;
import com.vijay.entities.CartItems;
import com.vijay.entities.Orders;
import com.vijay.repositories.AddressesRepository;
import com.vijay.repositories.CartItemsRepository;
import com.vijay.repositories.CartRepository;
import com.vijay.repositories.OrdersRepository;
import com.vijay.requests.PlaceOrderRequest;
import com.vijay.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    AddressesRepository addressesRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemsRepository cartItemsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    public GenericResponse placeOrder(PlaceOrderRequest placeOrderRequest) {

        Optional<Cart> cart = this.cartRepository.findById(placeOrderRequest.getCartId());
        Addresses addresses = this.saveAddress(placeOrderRequest.getAddress());
        Orders orders = new Orders();
        orders.setAddressId(addresses.getId());
        this.calculateOrderPrice(orders, placeOrderRequest);

        this.ordersRepository.save(orders);

        cart.get().setOrderId(orders.getId());
        this.cartRepository.save(cart.get());

        if (orders.getId() != null)
            return new GenericResponse(200, "Success");
        else
            return new GenericResponse(500, "An Error Occured");
    }

    private void calculateOrderPrice(Orders orders, PlaceOrderRequest placeOrderRequest) {

        orders.setPaymentMode(placeOrderRequest.getPaymentMode());
        Double orderPrice = this.cartItemsRepository.getCartPrice(placeOrderRequest.getCartId());
        Double totalAmount = orderPrice;
        if (orderPrice < 200) {
            totalAmount += 50;
        }
        orders.setTotalAmount(totalAmount);
        orders.setPrice(orderPrice);
        orders.setOrderStatus("Payment Started");
    }

    private Addresses saveAddress(String address) {

        Addresses addresses = new Addresses();
        addresses.setAddress(address);
        addressesRepository.save(addresses);
        return addresses;
    }
}
