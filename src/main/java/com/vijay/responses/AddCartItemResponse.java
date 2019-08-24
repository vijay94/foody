package com.vijay.responses;

public class AddCartItemResponse {

    private long cartId;

    public AddCartItemResponse(Long cartId) {
        this.cartId = cartId;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
}
