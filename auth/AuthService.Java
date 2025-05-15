package auth;

import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AuthService {
    private Map<String, User> users;
    private static final int SALT_LENGTH = 16;
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private static final long LOCKOUT_DURATION = 15 * 60 * 1000; // 15 minutes in milliseconds

    public AuthService() {
        this.users = new HashMap<>();
    }

    public class User {
        private String username;
        private String hashedPassword;
        private String salt;
        private int loginAttempts;
        private long lockoutTime;
        private boolean isLocked;

        public User(String username, String hashedPassword, String salt) {
            this.username = username;
            this.hashedPassword = hashedPassword;
            this.salt = salt;
            this.loginAttempts = 0;
            this.lockoutTime = 0;
            this.isLocked = false;
        }
    }

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists");
            return false;
        }

        try {
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);
            users.put(username, new User(username, hashedPassword, salt));
            System.out.println("User registered successfully");
            return true;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error during registration: " + e.getMessage());
            return false;
        }
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user == null) {
            System.out.println("User not found");
            return false;
        }

        if (user.isLocked) {
            if (System.currentTimeMillis() - user.lockoutTime < LOCKOUT_DURATION) {
                System.out.println("Account is locked. Try again later.");
                return false;
            } else {
                user.isLocked = false;
                user.loginAttempts = 0;
            }
        }

        try {
            String hashedInput = hashPassword(password, user.salt);
            if (hashedInput.equals(user.hashedPassword)) {
                user.loginAttempts = 0;
                System.out.println("Login successful");
                return true;
            } else {
                user.loginAttempts++;
                if (user.loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                    user.isLocked = true;
                    user.lockoutTime = System.currentTimeMillis();
                    System.out.println("Account locked due to too many failed attempts");
                } else {
                    System.out.println("Invalid password. Attempts remaining: " + 
                        (MAX_LOGIN_ATTEMPTS - user.loginAttempts));
                }
                return false;
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error during login: " + e.getMessage());
            return false;
        }
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes());
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = users.get(username);
        if (user == null) {
            System.out.println("User not found");
            return false;
        }

        try {
            String hashedOldPassword = hashPassword(oldPassword, user.salt);
            if (!hashedOldPassword.equals(user.hashedPassword)) {
                System.out.println("Current password is incorrect");
                return false;
            }

            String newSalt = generateSalt();
            String hashedNewPassword = hashPassword(newPassword, newSalt);
            user.hashedPassword = hashedNewPassword;
            user.salt = newSalt;
            System.out.println("Password changed successfully");
            return true;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error changing password: " + e.getMessage());
            return false;
        }
    }

    public void displayUserInfo(String username) {
        User user = users.get(username);
        if (user == null) {
            System.out.println("User not found");
            return;
        }

        System.out.println("\nUser Information:");
        System.out.println("Username: " + user.username);
        System.out.println("Account Status: " + (user.isLocked ? "Locked" : "Active"));
        if (user.isLocked) {
            long remainingTime = (LOCKOUT_DURATION - (System.currentTimeMillis() - user.lockoutTime)) / 1000;
            System.out.println("Lockout remaining: " + remainingTime + " seconds");
        }
        System.out.println("Failed login attempts: " + user.loginAttempts);
    }
}
