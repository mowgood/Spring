package com.example.userservice.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderResponse {

    private String productId;

    private int quantity;

    private int unitPrice;

    private int totalPrice;

    private LocalDateTime createdAt;

    private String orderId;

    private String orderStatus;
}
