package com.example.productservice.global.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExceptionResponse {

    private String statusCode;

    private String message;

    @Builder
    public ExceptionResponse(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
