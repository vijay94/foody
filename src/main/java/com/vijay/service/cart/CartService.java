package com.vijay.service.cart;

import com.vijay.entities.Cart;
import com.vijay.entities.CartItems;
import com.vijay.entities.Dishes;
import com.vijay.repositories.CartItemsRepository;
import com.vijay.repositories.CartRepository;
import com.vijay.repositories.DishesRepository;
import com.vijay.requests.AddCartItemRequest;
import com.vijay.responses.AddCartItemResponse;
import com.vijay.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    DishesRepository dishesRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemsRepository cartItemsRepository;

    public GenericResponse getCart(long userId) {

        Optional<Cart> cart = cartRepository.findCartByUserId(userId);

        if (!cart.isPresent())
            return new GenericResponse(404, "Cart is Empty");

        return new GenericResponse(200, cart,"success");
    }

    public GenericResponse addItemToCart(Long userId, AddCartItemRequest addCartItemRequest) {

        Optional<Dishes> dishes = dishesRepository.findById(addCartItemRequest.getDishId());

        if (dishes.isPresent()) {
            Optional<Cart> cart = cartRepository.findCartByUserId(userId);

            Long cartId;
            if (!cart.isPresent())
                cartId = this.createCart(userId);
            else
                cartId = cart.get().getId();

            CartItems cartItem = this.createCartItem(dishes.get(), cartId, userId, addCartItemRequest);
            return new GenericResponse(200,  "Success");
        } else {
            return new GenericResponse(404, "Item Not found");
        }
    }

    private CartItems createCartItem(Dishes dishes, Long cartId, Long userId, AddCartItemRequest addCartItemRequest) {

        CartItems cartItem = new CartItems();
        cartItem.setDishId(dishes.getId());
        cartItem.setPrice(dishes.getPrice());
        cartItem.setCartId(cartId);
        cartItem.setQuantity(addCartItemRequest.getQuantity());

        cartItemsRepository.save(cartItem);

        return cartItem;
    }

    private Long createCart(Long userId) {

        Cart cart = new Cart();
        cart.setUserId(userId);
        this.cartRepository.save(cart);
        return cart.getId();
    }
}
