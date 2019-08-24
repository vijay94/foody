package com.vijay.repositories;

import com.vijay.entities.ReviewPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReviewPointsRepository extends JpaRepository<ReviewPoints, Integer>, JpaSpecificationExecutor<ReviewPoints> {

}