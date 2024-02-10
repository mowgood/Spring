package com.example.orderservice.exception;

import com.example.orderservice.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class ProductStockException extends GlobalException {

    private static final String MESSAGE = "재고가 부족합니다.";

    public ProductStockException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
