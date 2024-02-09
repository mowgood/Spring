package com.example.orderservice.repository.mapping;

import com.example.orderservice.enumeration.OrderStatus;

import java.time.LocalDateTime;

public interface OrderGetMapping {

    String getProductId();

    int getQuantity();

    int getUnitPrice();

    int getTotalPrice();

    String getOrderId();

    LocalDateTime getCreatedAt();

    OrderStatus getOrderStatus();
}
