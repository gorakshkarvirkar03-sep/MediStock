
package model;

public class User {

    private int userId;
    private String username;
    private String passwordHash;
    private String fullName;
    private String role;

    public User(int userId, String username,
                String passwordHash, String fullName,
                String role) {

        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.role = role;
    }

    public User(String username, String passwordHash,
                String fullName, String role) {

        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

