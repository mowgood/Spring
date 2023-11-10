package com.example.userservice.repository;

import com.example.userservice.domain.User;
import com.example.userservice.repository.mapping.UserGetMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);

    UserGetMapping findByUserId(String userId);
}
