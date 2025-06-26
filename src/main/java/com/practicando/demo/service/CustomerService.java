package com.practicando.demo.service;

import com.practicando.demo.dto.CustomerDTO;
import com.practicando.demo.dto.DeleteCustomer;
import com.practicando.demo.entity.Customer;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CustomerService {
    Customer createCustomer(CustomerDTO customerDTO);
    Customer updateCustomer(Long id, CustomerDTO customerDTO);
    DeleteCustomer deleteCustomer(Long id);
    Customer findCustomerById(Long id);
    List<Customer> listCustomers(Pageable pageable);
}
