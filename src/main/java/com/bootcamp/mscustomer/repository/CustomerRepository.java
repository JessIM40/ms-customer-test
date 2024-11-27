package com.bootcamp.mscustomer.repository;

import com.bootcamp.mscustomer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByDni(String dni);
}
