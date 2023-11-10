package com.example.userservice.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateResponseDto {

    private Long savedId;

    @Builder
    public UserCreateResponseDto(Long savedId) {
        this.savedId = savedId;
    }
}
