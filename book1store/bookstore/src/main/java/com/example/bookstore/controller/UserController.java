package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        String token = String.valueOf(userService.loginUser(username, password));
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile() {
        User user = userService.getUserProfile();
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUserProfile(@RequestBody User updatedUser) {
        User user = userService.updateUserProfile(updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        boolean passwordChanged = userService.changeUserPassword(oldPassword, newPassword);
        if (passwordChanged) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password change failed");
        }
    }

    @PostMapping("/collection/add/{bookId}")
    public ResponseEntity<String> addBookToCollection(@PathVariable Long bookId) {
        boolean added = userService.addBookToUserCollection(bookId);
        if (added) {
            return ResponseEntity.ok("Book added to the collection");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found or already in the collection");
        }
    }

    @DeleteMapping("/collection/remove/{bookId}")
    public ResponseEntity<String> removeBookFromCollection(@PathVariable Long bookId) {
        boolean removed = userService.removeBookFromUserCollection(bookId);
        if (removed) {
            return ResponseEntity.ok("Book removed from the collection");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found in the collection");
        }
    }

    @GetMapping("/collection")
    public ResponseEntity<Set<Book>> getUserCollection() {
        Set<Book> collection = userService.getUserCollection();
        return ResponseEntity.ok(collection);
    }
}