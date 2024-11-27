package com.bootcamp.mscustomer.controller;

import com.bootcamp.mscustomer.model.entity.Customer;
import com.bootcamp.mscustomer.repository.CustomerRepository;
import com.bootcamp.mscustomer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Optional<Customer> customer = customerService.getCustomerById(id);

        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @PathVariable String id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
