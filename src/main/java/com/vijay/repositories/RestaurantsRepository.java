package com.vijay.repositories;

import com.vijay.responses.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.vijay.entities.Restaurants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Long>, JpaSpecificationExecutor<Restaurants> {

    @Query("select new com.vijay.responses.Restaurant(restaurant) from Restaurants restaurant where restaurant.restaurantName" +
            " like :searchTerm or restaurant.cousine like :searchTerm")
    List<Restaurant> search(@Param("searchTerm") String searchTerm);
}