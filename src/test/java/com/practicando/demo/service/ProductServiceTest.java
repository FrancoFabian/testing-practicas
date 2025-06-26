package com.practicando.demo.service;

import com.practicando.demo.dto.ProductDTO;
import com.practicando.demo.entity.Product;
import com.practicando.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProduct_shouldReturnProduct(){
        ProductDTO dto = ProductDTO.builder()
                .name("Condones")
                .stock(20)
                .price(300.56)
                .build();
        Product saved = Product.builder()
                .id(1L)
                .name("Condones")
                .stock(20)
                .price(300.56)
                .build();
        when(productRepository.save(any(Product.class))).thenReturn(saved);

        Product result = productService.createProduct(dto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Condones");
        assertThat(result.getStock()).isEqualTo(20);
        assertThat(result.getPrice()).isEqualTo(300.56);
        verify(productRepository,times(1)).save(any(Product.class));
    }

}
