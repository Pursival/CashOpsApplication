package com.fib.cashops.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class ApiKeyValidator {

    @Value("${fib.api-key.hash}")
    private String apiKeyHash;

    @Value("${fib.api-key.salt}")
    private String apiKeySalt;

    public boolean isValid(String apiKey) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest((apiKey + apiKeySalt).getBytes(StandardCharsets.UTF_8));
            String providedHash = Base64.getEncoder().encodeToString(hashedBytes);
            return providedHash.equals(apiKeyHash);
        } catch (Exception e) {
            return false;
        }
    }
}