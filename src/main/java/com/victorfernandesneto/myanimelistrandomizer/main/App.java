package com.victorfernandesneto.myanimelistrandomizer.main;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class App {
    public static void main(String[] args) {
        Locale currentLocale = Locale.getDefault();
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("config", currentLocale);
        } catch (Exception e) {
            System.err.println("Error loading configuration file: " + e.getMessage());
            return;
        }

        String apiUrlAuth = bundle.getString("api.url.auth");
        String codeVerifier = generateCodeVerifier();
        String codeChallenge;
        String requestUrlAuth;
        try {
            codeChallenge = generateCodeChallenge(codeVerifier);
            requestUrlAuth = (apiUrlAuth + codeChallenge);
            requestUrlAuth = requestUrlAuth.substring(0, requestUrlAuth.length()-1); // Taking away the '=' at the end.
            System.out.println(requestUrlAuth);
        } catch (Exception e) {
            System.err.println("Error generating code challenge: " + e.getMessage());
            // Handle the error appropriately (e.g., exit the program)
        }
    }

    public static String generateCodeVerifier() {
        byte[] randomBytes = new byte[32]; // Adjust size for desired security level
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }

    public static String generateCodeChallenge(String codeVerifier) throws Exception {
        String secret = "HmacSHA256"; // Use standard algorithm name
        Mac sha256HMAC = Mac.getInstance(secret);
        sha256HMAC.init(new SecretKeySpec(codeVerifier.getBytes("UTF-8"), secret));
        byte[] hash = sha256HMAC.doFinal();
        return Base64.getUrlEncoder().encodeToString(hash);
    }
}
