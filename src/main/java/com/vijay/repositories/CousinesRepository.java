package com.vijay.repositories;

import com.vijay.entities.Cousines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CousinesRepository extends JpaRepository<Cousines, Integer>, JpaSpecificationExecutor<Cousines> {

}