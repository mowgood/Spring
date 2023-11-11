package com.example.orderservice.global.exception.handler;

import com.example.orderservice.global.exception.GlobalException;
import com.example.orderservice.global.exception.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ExceptionResponse> globalException(GlobalException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(ExceptionResponse.builder()
                .statusCode(String.valueOf(exception.getStatusCode()))
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> basicException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder()
                .statusCode(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))
                .message("예기치 못한 오류가 발생했습니다. 다시 시도해 주세요.")
                .build());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionResponse> bindException(BindException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse.builder()
                .statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .message(Objects.requireNonNull(exception.getBindingResult().getFieldError().getDefaultMessage()))
                .build());
    }
}
