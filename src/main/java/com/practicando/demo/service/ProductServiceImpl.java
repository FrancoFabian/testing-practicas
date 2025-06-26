package com.practicando.demo.service;

import com.practicando.demo.dto.DeleteCustomer;
import com.practicando.demo.dto.ProductDTO;
import com.practicando.demo.entity.Product;
import com.practicando.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product createProduct(ProductDTO dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public DeleteCustomer deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return DeleteCustomer.builder()
                    .message("Product not found")
                    .success(false)
                    .build();
        }
        productRepository.deleteById(id);
        return DeleteCustomer.builder()
                .message("Product deleted")
                .success(true)
                .build();
    }

    @Override
    public List<Product> getAllProducts(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        return page.getContent();
    }
}
