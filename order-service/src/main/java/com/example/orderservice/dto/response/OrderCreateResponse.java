package com.example.orderservice.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCreateResponse {

    private Long savedId;

    @Builder
    public OrderCreateResponse(Long savedId) {
        this.savedId = savedId;
    }
}
