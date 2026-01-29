package com.example.URL.Shortener.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtil {

    public static String generateShortCode(String url) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash =md.digest(url.getBytes(StandardCharsets.UTF_8));

            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash).substring(0, 6);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash");
        }


    }
}
