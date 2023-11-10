package com.example.userservice.dto.response;

import com.example.userservice.enumeration.RoleType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UserGetResponse {

    private String userId;

    private String name;

    private RoleType roleType;

    private List<OrderResponse> orders;

    @Builder
    public UserGetResponse(String userId, String name, RoleType roleType, List<OrderResponse> orders) {
        this.userId = userId;
        this.name = name;
        this.roleType = roleType;
        this.orders = orders;
    }
}
