package com.handevcoder.auth_service.config;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    public JwtUtil() {
        try {
            this.privateKey = KeyLoader.loadPrivateKey("src/main/resources/keys/private-key.pem");
            this.publicKey = KeyLoader.loadPublicKey("src/main/resources/keys/public-key.pem");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final long EXPIRATION_TIME = 3600000;

    public String

}
