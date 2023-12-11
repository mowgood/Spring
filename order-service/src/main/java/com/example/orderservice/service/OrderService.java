package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.dto.request.OrderCreateRequest;
import com.example.orderservice.dto.response.OrderCreateResponse;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.mapping.OrderGetMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducer kafkaProducer;

    @Transactional
    public OrderCreateResponse createOrder(OrderCreateRequest request) {
        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .totalPrice(request.getQuantity() * request.getUnitPrice())
                .userId(request.getUserId())
                .orderId(UUID.randomUUID().toString())
                .build();

        orderRepository.save(order);

        kafkaProducer.send("product-stock-topic", order);

        return OrderCreateResponse.builder()
                .savedId(order.getId())
                .build();
    }

    @Transactional(readOnly = true)
    public List<OrderGetMapping> getAllOrder() {
        return orderRepository.findAllBy();
    }

    @Transactional(readOnly = true)
    public List<OrderGetMapping> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}