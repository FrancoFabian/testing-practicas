package com.practicando.demo.repository;

import com.practicando.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByIdIn(List<Long> ids);


    Optional<Product> findByName(String name);
}
