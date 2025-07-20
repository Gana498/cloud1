package com.company.lms.cloud1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.lms.cloud1.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}