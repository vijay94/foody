package com.vijay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.vijay.entities.Cousines;

public interface CousinesRepository extends JpaRepository<Cousines, Integer>, JpaSpecificationExecutor<Cousines> {

}