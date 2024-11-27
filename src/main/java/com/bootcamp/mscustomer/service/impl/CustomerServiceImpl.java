package com.bootcamp.mscustomer.service.impl;

import com.bootcamp.mscustomer.model.entity.Customer;
import com.bootcamp.mscustomer.repository.CustomerRepository;
import com.bootcamp.mscustomer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public  CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByDni(customer.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    @Override
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }


    @Override
    public Customer updateCustomer(String id, Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerDetails.getName());
                    customer.setLastname(customerDetails.getLastname());
                    customer.setDni(customerDetails.getDni());
                    customer.setEmail(customerDetails.getEmail());
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Cliento con ID: " + id + " no encontrado"));
    }


    @Override
    public void deleteCustomer(String id) {
        customerRepository.findById(id)
                .ifPresentOrElse(
                        customer -> {
                            if (customerHasActiveAccounts(customer)) {
                                throw new RuntimeException("No se puede eliminar clientes con cuentas activas");
                            }
                            customerRepository.delete(customer);
                        },
                        () -> {
                            throw new RuntimeException("No se encontró al cliente con ID: " + id);
                        }
                );
    }

    private boolean customerHasActiveAccounts(Customer customer) {
        return false;
        //return accountRepository.existsByCustomerId(customer.getId());
    }
}