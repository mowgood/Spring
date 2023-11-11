package com.example.orderservice.repository;

import com.example.orderservice.domain.Order;
import com.example.orderservice.repository.mapping.OrderGetMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<OrderGetMapping> findAllBy();
}
