package com.saivamsi.layers.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGenerator {

    public static KeyPair generateRsaKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException("could not generate rsa key");
        }
    }
}
