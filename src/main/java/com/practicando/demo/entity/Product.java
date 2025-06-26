package com.practicando.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    @SequenceGenerator(name = "product_seq_gen", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column
    private Double price;

    @Column(nullable = false)
    private Integer stock;
}