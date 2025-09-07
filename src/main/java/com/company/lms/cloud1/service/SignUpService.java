package com.company.lms.cloud1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.lms.cloud1.interfaces.SignUpServiceInterface;
import com.company.lms.cloud1.model.Role;
import com.company.lms.cloud1.model.User;
import com.company.lms.cloud1.model.UserDetails;
import com.company.lms.cloud1.repository.RoleRepository;
import com.company.lms.cloud1.repository.UserDetailsRepository;
import com.company.lms.cloud1.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class SignUpService implements SignUpServiceInterface {
    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository to handle database operations
    @Autowired
    private RoleRepository roleRepository; // Assuming you have a RoleRepository to handle roles
    @Autowired
    private UserDetailsRepository userDetailsRepository; // Assuming you have a UserDetailsRepository for user details

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void signUpUser(String username, String password, String firstName, String lastName, String email,
            String phoneNumber) {
                
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Create a new user entity
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Ensure to encode the password before saving
        user.setEnabled(false);
        Role role = roleRepository.findByName("ROLE_USER").orElse(new Role("ROLE_USER"));
        user.getRoles().add(role);
        roleRepository.save(role);

        // Create and save user details
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setEmail(email);
        userDetails.setPhoneNumber(phoneNumber);
        userDetails.setUser(user); // Set the relationship
        // Save the user
        userRepository.save(user);
        userDetailsRepository.save(userDetails);
        
    }

}
