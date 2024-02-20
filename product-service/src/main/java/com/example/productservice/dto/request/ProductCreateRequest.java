package com.example.productservice.dto.request;

import com.example.productservice.enumeration.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateRequest {

    private String productName;

    private Category category;

    private int stock;

    private int unitPrice;

    @Builder
    public ProductCreateRequest(String productName, Category category, int stock, int unitPrice) {
        this.productName = productName;
        this.category = category;
        this.stock = stock;
        this.unitPrice = unitPrice;
    }
}
