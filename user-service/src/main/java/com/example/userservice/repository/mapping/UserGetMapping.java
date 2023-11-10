package com.example.userservice.repository.mapping;

import com.example.userservice.enumeration.RoleType;

public interface UserGetMapping {

    String getUserId();

    String getName();

    RoleType getRoleType();
}
