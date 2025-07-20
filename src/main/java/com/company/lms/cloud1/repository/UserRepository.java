package com.company.lms.cloud1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.lsm.springh2withauth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}