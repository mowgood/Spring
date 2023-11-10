package com.example.userservice.exception;

import com.example.userservice.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class UserIdDuplicatedException extends GlobalException {

    private static final String MESSAGE = "중복된 아이디 입니다.";

    public UserIdDuplicatedException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
