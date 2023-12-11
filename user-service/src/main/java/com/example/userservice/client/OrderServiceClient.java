package com.example.userservice.client;

import com.example.userservice.dto.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="order-service")
public interface OrderServiceClient {

    @GetMapping("/{userId}/orders")
    List<OrderResponse> getOrdersByUserId(@PathVariable String userId);
}