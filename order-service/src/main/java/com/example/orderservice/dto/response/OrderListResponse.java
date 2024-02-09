package com.example.orderservice.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderListResponse {

    String productId;

    int quantity;

    int unitPrice;

    int totalPrice;

    String orderId;

    LocalDateTime createdAt;

    String orderStatus;

    @Builder
    public OrderListResponse(String productId, int quantity, int unitPrice, int totalPrice, String orderId,
                             LocalDateTime createdAt, String orderStatus) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.orderStatus = orderStatus;
    }
}
