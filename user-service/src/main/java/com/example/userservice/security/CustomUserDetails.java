package com.example.userservice.security;

import com.example.userservice.enumeration.RoleType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private Long id;

    private String userId;

    private String password;

    private String name;

    private RoleType roleType;

    private Collection<GrantedAuthority> authorities;

    @Builder
    public CustomUserDetails(Long id, String userId, String password, String name, RoleType roleType,
                             Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.roleType = roleType;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
