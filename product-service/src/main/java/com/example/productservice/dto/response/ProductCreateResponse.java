package com.example.productservice.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateResponse {

    private Long savedId;

    @Builder
    public ProductCreateResponse(Long savedId) {
        this.savedId = savedId;
    }
}