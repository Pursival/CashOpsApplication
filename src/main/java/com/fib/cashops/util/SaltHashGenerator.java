package com.fib.cashops.util;
import java.security.MessageDigest;
import java.util.Base64;

public class SaltHashGenerator {
    public static String hashWithSalt(String apiKey, String salt) throws Exception {
        String combined = apiKey + salt;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(combined.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hash);
    }
}