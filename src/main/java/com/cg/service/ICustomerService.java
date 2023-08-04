package com.cg.service;

import com.cg.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAll();

    Customer getById(Long id);

    void add(Customer customer);

    void update(Customer customer);
    void delete(Long id);
}
