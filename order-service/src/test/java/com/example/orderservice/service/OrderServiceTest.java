package com.example.orderservice.service;

import com.example.orderservice.dto.response.OrderListResponse;
import com.example.orderservice.enumeration.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void 주문_전체_조회() throws Exception {
        // given

        // when
        List<OrderListResponse> orderList = orderService.getAllOrder();

        // then
        assertEquals(OrderStatus.PAYMENT_COMPLETED.getValue(), orderList.get(0).getOrderStatus());
    }

    @Test
    void 아이디로_주문_조회() throws Exception {
        // given
        String userId = "misun";

        // when
        List<OrderListResponse> orderList = orderService.getOrdersByUserId(userId);

        // then
        assertEquals(OrderStatus.PAYMENT_COMPLETED.getValue(), orderList.get(0).getOrderStatus());
    }
}