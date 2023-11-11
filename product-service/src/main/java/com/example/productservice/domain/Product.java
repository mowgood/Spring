package com.example.productservice.domain;

import com.example.productservice.enumeration.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String productId;

    @NotBlank
    private String productName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotBlank
    private int stock;

    @NotBlank
    private int unitPrice;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Product(String productId, String productName, Category category, int stock, int unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.stock = stock;
        this.unitPrice = unitPrice;
    }
}
