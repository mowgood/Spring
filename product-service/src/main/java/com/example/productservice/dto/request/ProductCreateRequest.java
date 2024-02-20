package com.example.productservice.dto.request;

import com.example.productservice.enumeration.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateRequest {

    @NotBlank(message = "상품 이름을 입력하세요.")
    private String productName;
    
    private Category category;

    @NotNull(message = "상품 수량을 입력하세요.")
    private int stock;

    @NotNull(message = "상품 가격을 입력하세요.")
    private int unitPrice;

    @Builder
    public ProductCreateRequest(String productName, Category category, int stock, int unitPrice) {
        this.productName = productName;
        this.category = category;
        this.stock = stock;
        this.unitPrice = unitPrice;
    }
}
