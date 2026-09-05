package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên sản phẩm không được trống")
    @Column(nullable = false)
    private String name;

    private String description;

    @Positive(message = "Giá phải > 0")
    @Column(nullable = false)
    private Double price;

    @Positive(message = "Số lượng phải > 0")
    private Integer quantity = 0;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String imageUrl;

    @Column(columnDefinition = "BOOLEAN DEFAULT 1")
    private Boolean status = true;

    private Long createdAt = System.currentTimeMillis();
    private Long updatedAt = System.currentTimeMillis();
}
