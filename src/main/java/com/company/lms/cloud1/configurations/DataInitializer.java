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
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            User adminUser = userRepository.findByUsername("admin");
            // Check if the user "admin" exists, if not create it
            if (adminUser == null) {
                adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword("admin123");
                adminUser.setEnabled(true);
                userRepository.save(adminUser);
            }
            if (!adminUser.isEnabled()) {
                adminUser.setEnabled(true);
                userRepository.save(adminUser);
            }
            if (!adminUser.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"))) {
                Role adminRole = roleRepository.findByName("ADMIN").orElse(new Role("ADMIN"));
                adminUser.getRoles().add(adminRole);
                roleRepository.save(adminRole);
                userRepository.save(adminUser);
            }

        };
    }

}
