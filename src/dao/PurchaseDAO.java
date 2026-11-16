
package dao;

import model.Purchase;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {

    // Add Purchase and Update Medicine Stock
    public boolean addPurchase(Purchase purchase) {

        String purchaseSql = "INSERT INTO purchases " +
            "(medicine_id, supplier_id, purchase_date, quantity, purchase_price) " +
            "VALUES (?, ?, ?, ?, ?)";

        String stockSql = "UPDATE medicines " +
            "SET quantity = quantity + ? " +
            "WHERE medicine_id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);

            try (
                PreparedStatement purchasePs =
                    conn.prepareStatement(purchaseSql);

                PreparedStatement stockPs =
                    conn.prepareStatement(stockSql)
            ) {

                purchasePs.setInt(1, purchase.getMedicineId());
                purchasePs.setInt(2, purchase.getSupplierId());
                purchasePs.setDate(3, purchase.getPurchaseDate());
                purchasePs.setInt(4, purchase.getQuantity());
                purchasePs.setDouble(5, purchase.getPurchasePrice());

                purchasePs.executeUpdate();

                stockPs.setInt(1, purchase.getQuantity());
                stockPs.setInt(2, purchase.getMedicineId());

                int rows = stockPs.executeUpdate();

                if (rows == 0) {
                    throw new SQLException("Medicine not found.");
                }

                conn.commit();

                return true;

            } catch (SQLException e) {

                conn.rollback();
                System.out.println("Purchase failed: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {

            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    // View All Purchases
    public List<Purchase> getAllPurchases() {

        List<Purchase> purchases = new ArrayList<>();

        String sql = "SELECT * FROM purchases";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Purchase purchase = new Purchase(
                    rs.getInt("purchase_id"),
                    rs.getInt("medicine_id"),
                    rs.getInt("supplier_id"),
                    rs.getDate("purchase_date"),
                    rs.getInt("quantity"),
                    rs.getDouble("purchase_price")
                );

                purchases.add(purchase);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching purchases: " + e.getMessage());
        }

        return purchases;
    }
}
