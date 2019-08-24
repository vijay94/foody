package com.vijay.repositories;

import com.vijay.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {

    @Query("From Cart where userId = :userId and orderId is NULL")
    Optional<Cart> findCartByUserId(@Param("userId") long userId);


}