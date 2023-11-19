package com.example.productservice.domain;

import com.example.productservice.enumeration.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String productId;

    @NotNull
    private String productName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull
    private int stock;

    @NotNull
    private int unitPrice;

    @Builder
    public Product(String productId, String productName, Category category, int stock, int unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.stock = stock;
        this.unitPrice = unitPrice;
    }

    public void updateStock(int quantity) {
        this.stock -= quantity;
    }
}
