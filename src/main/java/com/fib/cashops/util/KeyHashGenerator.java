package com.fib.cashops.util;

public class KeyHashGenerator {
    public static void main(String[] args) throws Exception {
        String apiKey = "f9Uie8nNf112hx8s";
        String salt = "K4u9q0SdLz!2@%Zx";
        String hash = SaltHashGenerator.hashWithSalt(apiKey, salt);
        System.out.println("Store this hash: " + hash);
    }
}