package com.example.orderservice.controller;

import com.example.orderservice.dto.request.OrderCreateRequest;
import com.example.orderservice.dto.response.OrderCreateResponse;
import com.example.orderservice.repository.mapping.OrderGetMapping;
import com.example.orderservice.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        return String.format("Order-Service Success : %s", request.getServerPort());
    }

    @PostMapping("/orders")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid OrderCreateRequest request) {
        OrderCreateResponse response = orderService.createOrder(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{orderId}")
                .buildAndExpand(response.getSavedId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderGetMapping>> getOrderList() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }
}
