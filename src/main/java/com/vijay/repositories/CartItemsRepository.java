package com.vijay.repositories;

import com.vijay.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long>, JpaSpecificationExecutor<CartItems> {

    @Query("Select sum(cartItem.price * cartItem.quantity) from CartItems cartItem where cartItem.cartId = :cartId")
    Double getCartPrice(@Param("cartId") Long cartId);
}