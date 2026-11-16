
package service;

import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import model.User;
import util.PasswordUtil;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    // Register a new staff user
    public boolean registerStaff(
            String username,
            String password,
            String fullName) {

        try {
            if (username == null || username.trim().isEmpty()
                    || fullName == null || fullName.trim().isEmpty()
                    || password == null || password.length() < 8) {

                throw new IllegalArgumentException(
                    "Enter valid details. Password must be at least 8 characters."
                );
            }

            String passwordHash =
                    PasswordUtil.hashPassword(password);

            User user = new User(
                username.trim(),
                passwordHash,
                fullName.trim(),
                "STAFF"
            );

            return userDAO.registerUser(user);

        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;

        } catch (Exception e) {
            System.out.println("Password security error: " + e.getMessage());
            return false;
        }
    }

    // Login an existing user
    public User login(String username, String password) {

        try {
            if (username == null || username.trim().isEmpty()
                    || password == null || password.isEmpty()) {

                return null;
            }

            User user =
                    userDAO.getUserByUsername(username.trim());

            if (user == null) {
                return null;
            }

            boolean validPassword =
                    PasswordUtil.verifyPassword(
                        password,
                        user.getPasswordHash()
                    );

            if (validPassword) {
                return user;
            }

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return null;
    }

    // Create the first administrator account
    public boolean createFirstAdmin(
            String username,
            String password,
            String fullName) {

        try {
            if (username == null || username.trim().isEmpty()
                    || fullName == null || fullName.trim().isEmpty()
                    || password == null || password.length() < 12) {

                throw new IllegalArgumentException(
                    "Enter valid details. Admin password must be at least 12 characters."
                );
            }

            String passwordHash =
                    PasswordUtil.hashPassword(password);

            User admin = new User(
                username.trim(),
                passwordHash,
                fullName.trim(),
                "ADMIN"
            );

            return userDAO.createFirstAdmin(admin);

        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("Admin setup failed: " + e.getMessage());
            return false;

        } catch (Exception e) {
            System.out.println("Password security error: " + e.getMessage());
            return false;
        }
    }

    // View all registered users (admin access)
    public List<User> getAllUsers(User loggedInUser) {

        if (loggedInUser == null
                || !"ADMIN".equals(loggedInUser.getRole())) {

            System.out.println("Access denied! Admin permission required.");
            return new ArrayList<>();
        }

        try {
            return userDAO.getAllUsers();

        } catch (SQLException e) {
            System.out.println("User report error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
        // Reset an existing user's password
    public boolean resetPassword(String username, String newPassword) {

        try {
            if (username == null || username.trim().isEmpty()
                    || newPassword == null || newPassword.length() < 12) {

                throw new IllegalArgumentException(
                    "Username is required. Password must be at least 12 characters."
                );
            }

            String passwordHash =
                    PasswordUtil.hashPassword(newPassword);

            return userDAO.updatePassword(
                username.trim(),
                passwordHash
            );

        } catch (Exception e) {
            System.out.println("Password reset failed: " + e.getMessage());
            return false;
        }
    }    
}

