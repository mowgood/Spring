package com.example.orderservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCreateRequest {

    @NotNull(message = "상품 정보가 없습니다.")
    private Long productId;

    @NotNull(message = "수량을 선택하세요.")
    private int quantity;

    @NotNull(message = "유효하지 않은 요청입니다.")
    private int unitPrice;

    @NotBlank(message = "유효하지 않은 요청입니다.")
    private String userId;

    @Builder
    public OrderCreateRequest(Long productId, int quantity, int unitPrice, String userId) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.userId = userId;
    }
}
