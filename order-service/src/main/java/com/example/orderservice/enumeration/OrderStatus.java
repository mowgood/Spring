package com.example.orderservice.enumeration;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PAYMENT_PENDING("결제전"),
    PAYMENT_COMPLETED("결제 완료"),
    PREPARE("상품 준비중"),
    DELIVERING("배송중"),
    DELIVERED("배송 완료"),
    RETURNING("반품중"),
    RETURNED("반품 완료"),
    EXCHANGING("교환중"),
    EXCHANGED("교환 완료"),
    CANCEL("취소");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}