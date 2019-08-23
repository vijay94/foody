package com.vijay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.vijay.entities.Dishes;

public interface DishesRepository extends JpaRepository<Dishes, Long>, JpaSpecificationExecutor<Dishes> {

}