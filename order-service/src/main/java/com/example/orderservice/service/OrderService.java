package com.example.orderservice.service;

import com.example.orderservice.client.ProductServiceClient;
import com.example.orderservice.domain.Order;
import com.example.orderservice.dto.request.OrderCreateRequest;
import com.example.orderservice.dto.response.OrderCreateResponse;
import com.example.orderservice.dto.response.OrderListResponse;
import com.example.orderservice.enumeration.OrderStatus;
import com.example.orderservice.exception.ProductStockException;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.mapping.OrderGetMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final KafkaProducer kafkaProducer;

    private final ProductServiceClient productServiceClient;

    @Transactional
    public OrderCreateResponse createOrder(OrderCreateRequest request) {

        Integer stock = productServiceClient.getProductStock(request.getProductId());

        if (stock < request.getQuantity()) {
            throw new ProductStockException();
        }

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .totalPrice(request.getQuantity() * request.getUnitPrice())
                .userId(request.getUserId())
                .orderId(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                .build();

        orderRepository.save(order);

        kafkaProducer.send("product-stock-topic", order);

        return OrderCreateResponse.builder()
                .savedId(order.getId())
                .build();
    }

    @Transactional(readOnly = true)
    public List<OrderListResponse> getAllOrder() {
        List<OrderGetMapping> orders = orderRepository.findAllBy();
        List<OrderListResponse> orderList = new ArrayList<>(orders.size());
        orders.forEach(order -> {
            orderList.add(OrderListResponse.builder()
                    .productId(order.getProductId())
                    .quantity(order.getQuantity())
                    .unitPrice(order.getUnitPrice())
                    .totalPrice(order.getTotalPrice())
                    .orderId(order.getOrderId())
                    .createdAt(order.getCreatedAt())
                    .orderStatus(order.getOrderStatus().getValue())
                    .build());
        });
        return orderList;
    }

    @Transactional(readOnly = true)
    public List<OrderListResponse> getOrdersByUserId(String userId) {
        List<OrderGetMapping> orders = orderRepository.findByUserId(userId);
        List<OrderListResponse> orderList = new ArrayList<>(orders.size());
        orders.forEach(order -> {
            orderList.add(OrderListResponse.builder()
                    .productId(order.getProductId())
                    .quantity(order.getQuantity())
                    .unitPrice(order.getUnitPrice())
                    .totalPrice(order.getTotalPrice())
                    .orderId(order.getOrderId())
                    .createdAt(order.getCreatedAt())
                    .orderStatus(order.getOrderStatus().getValue())
                    .build());
        });
        return orderList;
    }
}