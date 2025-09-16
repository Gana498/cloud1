package com.company.lms.cloud1.configurations;

import org.springframework.context.annotation.Configuration;

import com.company.lms.cloud1.repository.RoleRepository;
import com.company.lms.cloud1.repository.UserRepository;
import com.company.lms.cloud1.model.Role;
import com.company.lms.cloud1.model.User;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserInitializer {
    // This class can be used to initialize data at application startup

    @Bean
    public CommandLineRunner initAdminUserData(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            User adminUser = userRepository.findByUsername("admin");
            // Check if the user "admin" exists, if not create it
            if (adminUser == null) {
                adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin123"));
                adminUser.setEnabled(true);
                userRepository.save(adminUser);
            }
            if (!adminUser.isEnabled()) {
                adminUser.setEnabled(true);
                userRepository.save(adminUser);
            }
            if (!adminUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) {
                Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElse(new Role("ROLE_ADMIN"));
                adminUser.getRoles().add(adminRole);
                roleRepository.save(adminRole);
                userRepository.save(adminUser);
            }

        };
    }

}
