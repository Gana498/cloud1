package com.company.lms.cloud1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.lms.cloud1.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

