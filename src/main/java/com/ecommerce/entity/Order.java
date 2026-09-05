package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double totalAmount = 0.0;

    @Column(nullable = false)
    private String status = "PENDING"; // PENDING, CONFIRMED, SHIPPING, DELIVERED, CANCELLED

    private String shippingAddress;
    private String notes;

    private Long createdAt = System.currentTimeMillis();
    private Long updatedAt = System.currentTimeMillis();
}
