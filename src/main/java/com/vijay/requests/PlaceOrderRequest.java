package com.vijay.requests;

import javax.validation.constraints.NotEmpty;

public class PlaceOrderRequest {

    @NotEmpty
    private long cartId;

    @NotEmpty
    private String address;

    @NotEmpty
    private String paymentMode;

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}
