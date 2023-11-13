package com.example.userservice.repository;

import com.example.userservice.domain.User;
import com.example.userservice.repository.mapping.UserGetMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);

    UserGetMapping findUserByUserId(String userId);

    Optional<User> findByUserId(String userId);
}
