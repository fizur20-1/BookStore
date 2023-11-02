package com.example.bookstore.service;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.User;

import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);

        user.setDateRegistered(new Date());

        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && new BCryptPasswordEncoder().matches(password, user.getPassword())) {

            return user;
        } else {
            return null;
        }
    }
    public User getUserProfile() {
        // Get the authenticated user by username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            return userRepository.findByUsername(username);
        }
        return null;
    }

//    public User getUserProfile() {
//        return userRepository.findByUsername(getUserProfile().getUsername());
//    }

    public User updateUserProfile(User updatedUser) {
        User existingUser = userRepository.findByUsername(getUserProfile().getUsername());

        if (existingUser != null) {
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());

            return userRepository.save(existingUser);
        }

        return null;
    }
    public boolean changeUserPassword(String oldPassword, String newPassword) {
        User authenticatedUser = getUserProfile();

        if (new BCryptPasswordEncoder().matches(oldPassword, authenticatedUser.getPassword())) {
            String hashedPassword = new BCryptPasswordEncoder().encode(newPassword);
            authenticatedUser.setPassword(hashedPassword);
            userRepository.save(authenticatedUser);
            return true;
        } else {
            return false;
        }
    }


    public boolean addBookToUserCollection(Long bookId) {
        User user = getUserProfile();
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user != null && book != null) {
            user.getBooks().add(book);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    public boolean removeBookFromUserCollection(Long bookId) {
        User user = getUserProfile();
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user != null && book != null) {
            user.getBooks().remove(book);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    public Set<Book> getUserCollection() {
        User user = getUserProfile();
        if (user != null) {
            return user.getBooks();
        }

        return Collections.emptySet();
    }
}
