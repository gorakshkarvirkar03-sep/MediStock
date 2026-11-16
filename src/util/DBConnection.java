
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
        "jdbc:mysql://127.0.0.1:3306/medistock_db";

    private static final String USER =
        System.getenv("MEDISTOCK_DB_USER");

    private static final String PASSWORD =
        System.getenv("MEDISTOCK_DB_PASSWORD");

    public static Connection getConnection()
            throws SQLException {

        if (USER == null || PASSWORD == null) {
            throw new SQLException(
                "Database environment variables are not configured."
            );
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                "MySQL JDBC Driver could not be loaded.", e
            );
        }

        return DriverManager.getConnection(
            URL, USER, PASSWORD
        );
    }
}
