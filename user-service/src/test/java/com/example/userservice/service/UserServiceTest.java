package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.request.UserCreateRequest;
import com.example.userservice.dto.response.UserCreateResponse;
import com.example.userservice.enumeration.RoleType;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void 유저_생성_성공() throws Exception {
        // given
        UserCreateRequest request = UserCreateRequest.builder()
                .userId("misun")
                .password("0000")
                .name("봉미선")
                .roleType(RoleType.ROLE_USER)
                .build();

        // when
        UserCreateResponse response = userService.createUser(request);

        // then
        Optional<User> user = userRepository.findById(response.getSavedId());

        assertEquals("misun", user.get().getUserId());
        assertEquals(passwordEncoder.matches("0000", user.get().getPassword()), true);
        assertEquals("봉미선", user.get().getName());
        assertEquals(RoleType.ROLE_USER, user.get().getRoleType());
    }
}