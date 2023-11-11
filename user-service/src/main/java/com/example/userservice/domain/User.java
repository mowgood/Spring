package com.example.userservice.domain;

import com.example.userservice.enumeration.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String userId;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Builder
    public User(String userId, String password, String name, RoleType roleType) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.roleType = roleType;
    }
}
