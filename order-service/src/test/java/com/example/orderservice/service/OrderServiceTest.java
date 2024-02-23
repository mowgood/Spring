package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.dto.response.OrderListResponse;
import com.example.orderservice.enumeration.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    private final String testOrderId = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        Order order = Order.builder()
                .productId(1L)
                .quantity(10)
                .unitPrice(10000)
                .totalPrice(100000)
                .userId("testuser")
                .orderId(testOrderId)
                .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                .build();

        Order order2 = Order.builder()
                .productId(2L)
                .quantity(2)
                .unitPrice(5000)
                .totalPrice(10000)
                .userId("happy")
                .orderId(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                .build();

        orderRepository.save(order);
        orderRepository.save(order2);
    }

    @Test
    void 주문_전체_조회() throws Exception {
        // given

        // when
        List<OrderListResponse> orderList = orderService.getAllOrder();
        int orderSize = orderRepository.findAll().size();

        // then
        assertEquals("1", orderList.get(0).getProductId().toString());
        assertEquals(10, orderList.get(0).getQuantity());
        assertEquals(10000, orderList.get(0).getUnitPrice());
        assertEquals(testOrderId, orderList.get(0).getOrderId());
        assertEquals(OrderStatus.PAYMENT_COMPLETED.getValue(), orderList.get(0).getOrderStatus());
        assertEquals(2, orderSize);
    }

    @Test
    void 아이디로_주문_조회() throws Exception {
        // given
        String userId = "testuser";

        // when
        List<OrderListResponse> orderList = orderService.getOrdersByUserId(userId);

        // then
        assertEquals("1", orderList.get(0).getProductId().toString());
        assertEquals(10, orderList.get(0).getQuantity());
        assertEquals(10000, orderList.get(0).getUnitPrice());
        assertEquals(testOrderId, orderList.get(0).getOrderId());
        assertEquals(OrderStatus.PAYMENT_COMPLETED.getValue(), orderList.get(0).getOrderStatus());
    }

    @Test
    void 아이디로_주문_조회_실패_없는아이디() throws Exception {
        // given
        String userId = "noneId";

        // when
        List<OrderListResponse> orderList = orderService.getOrdersByUserId(userId);

        // then
        assertEquals(0, orderList.size());
    }
}