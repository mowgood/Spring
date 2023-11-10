package com.example.userservice.exception;

import com.example.userservice.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GlobalException {

    private static final String MESSAGE = "유저를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
