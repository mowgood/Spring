package com.example.userservice.controller;

import com.example.userservice.dto.request.UserCreateRequest;
import com.example.userservice.dto.response.UserCreateResponse;
import com.example.userservice.dto.response.UserGetResponse;
import com.example.userservice.service.UserService;
import io.micrometer.core.annotation.Timed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/check")
    @Timed(value = "users.check", longTask = true)
    public String check(HttpServletRequest request) {
        return String.format("User-Service Success : %s", request.getServerPort());
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(response.getSavedId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserGetResponse> getUsers(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }
}