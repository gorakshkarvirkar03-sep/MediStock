
package dao;

import java.util.ArrayList;
import java.util.List;

import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Register a new user
    public boolean registerUser(User user) throws SQLException {

        String sql = "INSERT INTO users "
                   + "(username, password_hash, full_name, role) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getRole());

            return ps.executeUpdate() > 0;
        }
    }

    // Find a user using username
    public User getUserByUsername(String username)
            throws SQLException {

        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("full_name"),
                        rs.getString("role")
                    );
                }
            }
        }

        return null;
    }

    // Create the first administrator account only
    public boolean createFirstAdmin(User user) throws SQLException {

        String checkSql =
                "SELECT COUNT(*) FROM users WHERE role = 'ADMIN'";

        String insertSql =
                "INSERT INTO users "
              + "(username, password_hash, full_name, role) "
              + "VALUES (?, ?, ?, 'ADMIN')";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {
                try (PreparedStatement check =
                        con.prepareStatement(checkSql);
                     ResultSet rs = check.executeQuery()) {

                    rs.next();

                    if (rs.getInt(1) > 0) {
                        con.rollback();
                        return false;
                    }
                }

                try (PreparedStatement ps =
                        con.prepareStatement(insertSql)) {

                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPasswordHash());
                    ps.setString(3, user.getFullName());

                    boolean created = ps.executeUpdate() > 0;

                    con.commit();
                    return created;
                }

            } catch (SQLException | RuntimeException e) {
                con.rollback();
                throw e;

            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    // Retrieve all registered users
    public List<User> getAllUsers() throws SQLException {

        List<User> users = new ArrayList<>();

        String sql = "SELECT user_id, username, full_name, role "
                   + "FROM users ORDER BY user_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    null,
                    rs.getString("full_name"),
                    rs.getString("role")
                );

                users.add(user);
            }
        }

        return users;
    }

    // Update an existing user's password
    public boolean updatePassword(String username, String passwordHash)
            throws SQLException {

        String sql = "UPDATE users SET password_hash = ? WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, passwordHash);
            ps.setString(2, username);

            return ps.executeUpdate() > 0;
        }
    }

}

