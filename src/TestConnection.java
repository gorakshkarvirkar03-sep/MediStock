
import java.sql.Connection;
import java.sql.SQLException;

import util.DBConnection;

public class TestConnection {

    public static void main(String[] args) {

        try (Connection con = DBConnection.getConnection()) {

            if (con != null) {
                System.out.println("MediStock Database Connected!");
                System.out.println("Connection is working successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}
