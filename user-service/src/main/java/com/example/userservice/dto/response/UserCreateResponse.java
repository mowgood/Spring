package com.example.userservice.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateResponse {

    private Long savedId;

    @Builder
    public UserCreateResponse(Long savedId) {
        this.savedId = savedId;
    }
}
