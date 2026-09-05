package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên danh mục không được trống")
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(columnDefinition = "BOOLEAN DEFAULT 1")
    private Boolean status = true;

    private Long createdAt = System.currentTimeMillis();
}
