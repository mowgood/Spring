package com.example.userservice.dto.request;

import com.example.userservice.enumeration.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @NotBlank(message = "아이디를 입력하세요")
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    @NotBlank(message = "이름을 입력하세요")
    private String name;

    @NotNull(message = "유효하지 않은 권한입니다")
    private RoleType roleType;

    @Builder
    public UserCreateRequest(String userId, String password, String name, RoleType roleType) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.roleType = roleType;
    }
}
