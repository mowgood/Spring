package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.request.UserCreateRequest;
import com.example.userservice.dto.response.OrderResponse;
import com.example.userservice.dto.response.UserCreateResponse;
import com.example.userservice.dto.response.UserGetResponse;
import com.example.userservice.exception.UserIdDuplicatedException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.repository.mapping.UserGetMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserCreateResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new UserIdDuplicatedException();
        }

        User user = User.builder()
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .roleType(request.getRoleType())
                .build();

        userRepository.save(user);

        return UserCreateResponse.builder()
                .savedId(user.getId())
                .build();
    }

    @Transactional(readOnly = true)
    public UserGetResponse getUserByUserId(String userId) {
        UserGetMapping user = userRepository.findUserByUserId(userId);

        if(user == null) {
            throw new UserNotFoundException();
        }

        List<OrderResponse> orderList = new ArrayList<>();

        return UserGetResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .roleType(user.getRoleType())
                .orders(orderList)
                .build();
    }
}
