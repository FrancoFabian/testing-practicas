package com.practicando.demo.repository;

import com.practicando.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    void deleteById(Long id);
}
