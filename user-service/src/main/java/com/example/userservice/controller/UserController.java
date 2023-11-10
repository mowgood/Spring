package com.example.userservice.controller;

import com.example.userservice.dto.request.UserCreateRequestDto;
import com.example.userservice.dto.response.UserCreateResponseDto;
import com.example.userservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        return String.format("User-Service Success : %s", request.getServerPort());
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserCreateRequestDto request) {
        UserCreateResponseDto response = userService.createUser(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(response.getSavedId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
