package com.bootcamp.mscustomer.service;

import com.bootcamp.mscustomer.model.entity.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerService {

    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(String id);
    Customer updateCustomer(String id, Customer customerDetails);
    void deleteCustomer(String id);

}
