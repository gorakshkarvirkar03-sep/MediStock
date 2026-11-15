package dao;

import model.Medicine;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MedicineDAO {

    public List<Medicine> getAllMedicines() {

        List<Medicine> medicines = new ArrayList<>();

        String sql = "SELECT * FROM medicines";

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

                medicines.add(medicine);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving medicines!");
            e.printStackTrace();
        }

        return medicines;

    }

public boolean addMedicine(Medicine medicine) {

    String sql = "INSERT INTO medicines " +
        "(medicine_name, manufacturer, category, price, quantity, expiry_date) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, medicine.getMedicineName());
        ps.setString(2, medicine.getManufacturer());
        ps.setString(3, medicine.getCategory());
        ps.setDouble(4, medicine.getPrice());
        ps.setInt(5, medicine.getQuantity());
        ps.setDate(6, java.sql.Date.valueOf(medicine.getExpiryDate()));

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error adding medicine!");
        e.printStackTrace();
        return false;
    }
}

public boolean updateMedicine(int medicineId, double price, int quantity) {

    String sql = "UPDATE medicines SET price = ?, quantity = ? " +
                 "WHERE medicine_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDouble(1, price);
        ps.setInt(2, quantity);
        ps.setInt(3, medicineId);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error updating medicine!");
        e.printStackTrace();
        return false;
    }
}

public boolean deleteMedicine(int medicineId) {

    String sql = "DELETE FROM medicines WHERE medicine_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, medicineId);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error deleting medicine!");
        e.printStackTrace();
        return false;
    }
}


}
