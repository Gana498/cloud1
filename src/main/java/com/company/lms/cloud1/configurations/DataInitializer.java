package com.company.lms.cloud1.configurations;

import org.springframework.context.annotation.Configuration;
import java.util.Set;

import com.company.lms.cloud1.repository.RoleRepository;
import com.company.lms.cloud1.repository.UserRepository;
import com.company.lms.cloud1.model.Role;
import com.company.lms.cloud1.model.User;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
@Configuration
public class DataInitializer {
    // This class can be used to initialize data at application startup
    
    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository,UserRepository userRepository) {
        return args -> {
            // Check if the user "admin" exists, if not create it
            if(userRepository.findByUsername("admin")==null) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword("admin123");
            adminUser.setRoles(Set.of(adminRole));
            adminUser.setEnabled(true); // Set the user as enabled
            userRepository.save(adminUser);
            }
        };
    }
    
}

