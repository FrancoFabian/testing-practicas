package com.practicando.demo.service;

import com.practicando.demo.dto.ProductDTO;
import com.practicando.demo.entity.Product;
import com.practicando.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceIntegrationTest {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createProduct_shouldPersistProductInDatabase() {
        ProductDTO dto = ProductDTO.builder()
                .name("Condones")
                .stock(20)
                .price(300.56)
                .build();

        Product saved = productService.createProduct(dto);

        // Consulta en la BD real (H2)
        Product found = productRepository.findById(saved.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Condones");
        assertThat(found.getStock()).isEqualTo(20);
        assertThat(found.getPrice()).isEqualTo(300.56);
    }

}
