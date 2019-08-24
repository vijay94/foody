package com.vijay.requests;

import javax.validation.constraints.NotEmpty;

public class AddCartItemRequest {

    @NotEmpty
    private Long dishId;

    @NotEmpty
    private Integer quantity;

    private Long cartId;

    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
