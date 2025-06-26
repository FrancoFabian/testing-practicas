package com.practicando.demo.service;

import com.practicando.demo.dto.DeleteCustomer;
import com.practicando.demo.dto.ProductDTO;
import com.practicando.demo.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductDTO productDTO);
    DeleteCustomer deleteProduct(Long id);
    List<Product> getAllProducts(Pageable pageable);
}
