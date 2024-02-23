package com.example.orderservice.domain;

import com.example.orderservice.enumeration.OrderStatus;
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
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private int quantity;

    @NotNull
    private int unitPrice;

    @NotNull
    private int totalPrice;

    @NotNull
    private String userId;

    @NotNull
    @Column(unique = true)
    private String orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Long productId, int quantity, int unitPrice, int totalPrice, String userId,
                 String orderId, OrderStatus orderStatus) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
