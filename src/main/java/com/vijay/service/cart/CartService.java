package com.vijay.service.cart;

import com.vijay.entities.Cart;
import com.vijay.entities.CartItems;
import com.vijay.entities.Dishes;
import com.vijay.repositories.CartItemsRepository;
import com.vijay.repositories.CartRepository;
import com.vijay.repositories.DishesRepository;
import com.vijay.requests.AddCartItemRequest;
import com.vijay.responses.GenericResponse;
import com.vijay.responses.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    DishesRepository dishesRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemsRepository cartItemsRepository;

    public GenericResponse getCart(long cartId) {

        Optional<Cart> cart = cartRepository.findById(cartId);

        return new GenericResponse(200, cart,"success");
    }

    public GenericResponse addItemToCart(AddCartItemRequest addCartItemRequest) {

        Optional<Dishes> dishes = dishesRepository.findById(addCartItemRequest.getDishId());

        if (dishes.isPresent()) {

            CartItems cartItem = this.createCartItem(dishes.get(), addCartItemRequest);
            return new GenericResponse(200, "Success");
        } else {
            return new GenericResponse(404, "Item Not found");
        }
    }

    private CartItems createCartItem(Dishes dishes, AddCartItemRequest addCartItemRequest) {

        Long cartId = addCartItemRequest.getCartId();
        if (addCartItemRequest.getCartId() == null)
            cartId = this.createCart();

        CartItems cartItem = new CartItems();
        cartItem.setDishId(dishes.getId());
        cartItem.setPrice(dishes.getPrice());
        cartItem.setCartId(cartId);
        cartItem.setQuantity(addCartItemRequest.getQuantity());

        cartItemsRepository.save(cartItem);

        return cartItem;
    }

    private Long createCart() {

        Cart cart = new Cart();
        this.cartRepository.save(cart);
        return cart.getId();
    }
}
