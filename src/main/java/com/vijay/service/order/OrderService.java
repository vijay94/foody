package com.vijay.service.order;

import com.vijay.entities.Addresses;
import com.vijay.entities.Cart;
import com.vijay.entities.Orders;
import com.vijay.entities.Users;
import com.vijay.repositories.*;
import com.vijay.requests.PlaceOrderRequest;
import com.vijay.responses.GenericResponse;
import com.vijay.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    AddressesRepository addressesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemsRepository cartItemsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    public GenericResponse placeOrder(Long userId, PlaceOrderRequest placeOrderRequest) {

        Optional<Cart> cart = this.cartRepository.findCartByUserId(userId);

        if (cart.isPresent()) {
            Addresses addresses = this.saveAddress(userId, placeOrderRequest.getAddress());
            placeOrderRequest.setCartId(cart.get().getId());
            Orders orders = new Orders();
            orders.setUserId(userId);
            orders.setAddressId(addresses.getId());
            this.calculateOrderPriceAndSave(orders, placeOrderRequest);

            if (placeOrderRequest.isPreferedPaymentMode())
                this.saveUserPaymentPreference(userId, placeOrderRequest.getPaymentMode());

            // Process payment here by invoking payment service and handle the scenarios

            cart.get().setOrderId(orders.getId());
            this.cartRepository.save(cart.get());

            if (orders.getId() != null)
                return new GenericResponse(200, "Success");
        }

        return new GenericResponse(500, "An Error Occured");
    }

    private void saveUserPaymentPreference(Long userId, String paymentMode) {

//        Call the user service to save

        Optional<Users> user = this.usersRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setPreferedPaymentMode(paymentMode);
            this.usersRepository.save(user.get());
        }
    }

    private void calculateOrderPriceAndSave(Orders orders, PlaceOrderRequest placeOrderRequest) {

        orders.setPaymentMode(placeOrderRequest.getPaymentMode());
        Double orderPrice = this.cartItemsRepository.getCartPrice(placeOrderRequest.getCartId());
        Double totalAmount = orderPrice;
        if (orderPrice < Constants.MINIMUM_DELIVERY_OFF_PRICE) {
            totalAmount += Constants.DELIVERY_CHARGE;
        }
        orders.setTotalAmount(totalAmount);
        orders.setPrice(orderPrice);
        orders.setOrderStatus("Payment Started");
        this.ordersRepository.save(orders);
    }

    private Addresses saveAddress(Long userId, String address) {

        Addresses addresses = new Addresses();
        addresses.setAddress(address);
        addresses.setUserId(userId);
        addressesRepository.save(addresses);
        return addresses;
    }
}
