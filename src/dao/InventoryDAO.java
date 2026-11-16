
package dao;

import model.Medicine;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    // Retrieve all medicines from inventory
    public List<Medicine> getAllInventory() throws SQLException {

        List<Medicine> inventory = new ArrayList<>();

        String sql = "SELECT * FROM medicines ORDER BY medicine_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Medicine medicine = new Medicine(
                    rs.getInt("medicine_id"),
                    rs.getString("medicine_name"),
                    rs.getString("manufacturer"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getString("expiry_date")
                );

                inventory.add(medicine);
            }
        }

        return inventory;
    }

public List<Medicine> getLowStockMedicines(int threshold)
        throws SQLException {

    List<Medicine> medicines = new ArrayList<>();

    String sql = "SELECT * FROM medicines WHERE quantity <= ? "
               + "ORDER BY quantity ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, threshold);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Medicine medicine = new Medicine(
                    rs.getInt("medicine_id"),
                    rs.getString("medicine_name"),
                    rs.getString("manufacturer"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getString("expiry_date")
                );

                medicines.add(medicine);
            }
        }
    }

    return medicines;
}

public List<Medicine> getExpiringMedicines(int daysAhead)
        throws SQLException {

    if (daysAhead < 0) {
        throw new IllegalArgumentException(
            "Days ahead cannot be negative."
        );
    }

    List<Medicine> medicines = new ArrayList<>();

    String sql = "SELECT * FROM medicines "
               + "WHERE expiry_date <= DATE_ADD(CURDATE(), INTERVAL ? DAY) "
               + "ORDER BY expiry_date ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, daysAhead);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Medicine medicine = new Medicine(
                    rs.getInt("medicine_id"),
                    rs.getString("medicine_name"),
                    rs.getString("manufacturer"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getString("expiry_date")
                );

                medicines.add(medicine);
            }
        }
    }

    return medicines;
}


}

