
package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO {

    // 1. Calculate total medicines in stock
    public int getTotalStockQuantity() throws SQLException {

        String sql = "SELECT COALESCE(SUM(quantity), 0) FROM medicines";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }

    // 2. Calculate total inventory value
    public double getTotalInventoryValue() throws SQLException {

        String sql = "SELECT COALESCE(SUM(price * quantity), 0) FROM medicines";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }
        }

        return 0.0;
    }

    // 3. Calculate total sales revenue
    public double getTotalSalesRevenue() throws SQLException {

        String sql = "SELECT COALESCE(SUM(total_amount), 0) FROM sales";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }
        }

        return 0.0;
    }

    // 4. Count total sales transactions
    public int getTotalSalesCount() throws SQLException {

        String sql = "SELECT COUNT(*) FROM sales";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }
}

