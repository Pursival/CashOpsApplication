package com.fib.cashops.util;

public class KeyHashGenerator {
    public static void main(String[] args) throws Exception {
        String apiKey = "";
        String salt = "";
        String hash = SaltHashGenerator.hashWithSalt(apiKey, salt);
        System.out.println("Store this hash: " + hash);
    }
}