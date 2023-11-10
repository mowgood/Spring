package com.example.userservice.global.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExceptionResponseDto {

    private String statusCode;

    private String message;

    @Builder
    public ExceptionResponseDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
