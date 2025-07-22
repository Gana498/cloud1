package com.company.lms.cloud1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.lms.cloud1.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByUserId(Long id);
    
}
