package com.example.bookstore.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Configuration
public class JwtConfig {
    // Define your token secret key (Keep it secure!)
    private final String secret = "YourSecretKey"; // Change this to a secure secret key

    // Define the token expiration time (e.g., 1 hour)
    private final long expirationTime = 3600000;

    @Bean
    public Key key() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String getSecret() {
        return secret;
    }

    public long getExpirationTime() {
        return expirationTime;
    }
}
