
package util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {

    private static final int ITERATIONS = 210000;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 16;

    // Generate a secure password hash
    public static String hashPassword(String password)
            throws Exception {

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException(
                "Password cannot be empty."
            );
        }

        byte[] salt = new byte[SALT_LENGTH];

        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(
            password.toCharArray(),
            salt,
            ITERATIONS,
            KEY_LENGTH
        );

        try {
            SecretKeyFactory factory =
                SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256"
                );

            byte[] hash = factory.generateSecret(spec).getEncoded();

            return ITERATIONS + ":" +
                   Base64.getEncoder().encodeToString(salt) + ":" +
                   Base64.getEncoder().encodeToString(hash);

        } finally {
            spec.clearPassword();
        }
    }

    // Verify a password during login
    public static boolean verifyPassword(
            String password, String storedHash)
            throws Exception {

        if (password == null || storedHash == null) {
            return false;
        }

        String[] parts = storedHash.split(":");

        if (parts.length != 3) {
            return false;
        }

        try {
            int iterations = Integer.parseInt(parts[0]);

            if (iterations < 1 || iterations > 1000000) {
                return false;
            }

            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] expectedHash = Base64.getDecoder().decode(parts[2]);

            PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                iterations,
                expectedHash.length * 8
            );

            try {
                SecretKeyFactory factory =
                    SecretKeyFactory.getInstance(
                        "PBKDF2WithHmacSHA256"
                    );

                byte[] actualHash =
                    factory.generateSecret(spec).getEncoded();

                return MessageDigest.isEqual(
                    expectedHash, actualHash
                );

            } finally {
                spec.clearPassword();
            }

        } catch (IllegalArgumentException |
                 java.security.GeneralSecurityException e) {
            return false;
        }
    }
}

