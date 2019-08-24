package com.vijay.repositories;

import com.vijay.entities.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressesRepository extends JpaRepository<Addresses, Long>, JpaSpecificationExecutor<Addresses> {

}