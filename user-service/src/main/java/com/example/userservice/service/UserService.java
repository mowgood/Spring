package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.request.UserCreateRequest;
import com.example.userservice.dto.response.UserCreateResponse;
import com.example.userservice.exception.UserIdDuplicatedException;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
