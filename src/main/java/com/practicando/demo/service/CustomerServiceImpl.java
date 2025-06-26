package com.practicando.demo.service;

import com.practicando.demo.dto.CustomerDTO;
import com.practicando.demo.dto.DeleteCustomer;
import com.practicando.demo.entity.Customer;
import com.practicando.demo.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer createCustomer(CustomerDTO dto) {
        Customer customer = Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long id, CustomerDTO dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());

        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public DeleteCustomer deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            return DeleteCustomer.builder()
                    .message("Customer not found")
                    .success(false)
                    .build();
        }
        customerRepository.deleteById(id);
        return DeleteCustomer.builder()
                .message("Customer deleted")
                .success(true)
                .build();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    @Override
    public List<Customer> listCustomers(Pageable pageable) {
        Page<Customer> page = customerRepository.findAll(pageable);
        return page.getContent();
    }
}
