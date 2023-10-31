package com.example.bookstore.service;
import com.example.bookstore.entity.User;

import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Implement user registration, login, profile retrieval, and update methods

    public User registerUser(User user) {
        // Check if the username or email is already taken
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Hash the password before saving
        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);

        // Set the dateRegistered field to the current date
        user.setDateRegistered(new Date());

        // Save the user to the database
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            // Passwords match, so the user is authenticated
            // You can return the user or a JWT token for authentication
            return user;
        } else {
            // Authentication failed
            return null;
        }
    }

    public User getUserProfile() {
        return userRepository.findByUsername(getUserProfile().getUsername());
    }

    public User updateUserProfile(User updatedUser) {
        User existingUser = userRepository.findByUsername(getUserProfile().getUsername());

        if (existingUser != null) {
            // Update user details
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());

            // Save the updated user
            return userRepository.save(existingUser);
        }

        return null; // Handle the case where the user doesn't exist
    }
}
