package com.example.userservice.dto.response;

import lombok.Getter;

import java.util.Date;

@Getter
public class OrderResponse {

    private String productId;

    private int quantity;

    private int unitPrice;

    private int totalPrice;

    private Date createdAt;

    private String orderId;
}
