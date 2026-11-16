
package dao;

import model.Supplier;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public List<Supplier> getAllSuppliers() {

        List<Supplier> suppliers = new ArrayList<>();

        String sql = "SELECT * FROM suppliers";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Supplier supplier = new Supplier(
                    rs.getInt("supplier_id"),
                    rs.getString("supplier_name"),
                    rs.getString("contact_person"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("address")
                );

                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving suppliers: " + e.getMessage());
        }

        return suppliers;
    }

    // Add Supplier
    public boolean addSupplier(Supplier supplier) {

        String sql = "INSERT INTO suppliers " +
                     "(supplier_name, contact_person, phone, email, address) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getPhone());
            ps.setString(4, supplier.getEmail());
            ps.setString(5, supplier.getAddress());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding supplier: " + e.getMessage());
            return false;
        }
    }

    // Update Supplier
    public boolean updateSupplier(Supplier supplier) {

        String sql = "UPDATE suppliers SET " +
                     "supplier_name = ?, " +
                     "contact_person = ?, " +
                     "phone = ?, " +
                     "email = ?, " +
                     "address = ? " +
                     "WHERE supplier_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getPhone());
            ps.setString(4, supplier.getEmail());
            ps.setString(5, supplier.getAddress());
            ps.setInt(6, supplier.getSupplierId());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating supplier: " + e.getMessage());
            return false;
        }
    }

    // Delete Supplier
    public boolean deleteSupplier(int supplierId) {

        String sql = "DELETE FROM suppliers WHERE supplier_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, supplierId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting supplier: " + e.getMessage());
            return false;
        }
    }

}
