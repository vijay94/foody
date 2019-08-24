package com.vijay.repositories;

import com.vijay.entities.Restaurants;
import com.vijay.responses.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Long>, JpaSpecificationExecutor<Restaurants> {

    @Query("select new com.vijay.responses.Restaurant(restaurant, AVG(reviews.reviewPoint)) from Restaurants restaurant " +
            "inner join Reviews reviews on reviews.restaurantId = restaurant.id where restaurant.restaurantName" +
            " like :searchTerm group by restaurant")
    List<Restaurant> search(@Param("searchTerm") String searchTerm);
}