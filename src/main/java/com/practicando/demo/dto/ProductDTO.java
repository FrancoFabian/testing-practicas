package com.practicando.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ProductDTO {
    private String name;
    private Double price;
    private Integer stock;
}
