package com.vijay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.vijay.entities.RestaurantCousineMapping;

public interface RestaurantCousineMappingRepository extends JpaRepository<RestaurantCousineMapping, Long>, JpaSpecificationExecutor<RestaurantCousineMapping> {

}