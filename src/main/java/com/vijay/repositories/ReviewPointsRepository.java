package com.vijay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.vijay.entities.ReviewPoints;

public interface ReviewPointsRepository extends JpaRepository<ReviewPoints, Integer>, JpaSpecificationExecutor<ReviewPoints> {

}