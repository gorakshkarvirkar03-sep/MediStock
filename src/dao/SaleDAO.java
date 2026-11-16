
package dao;

import java.util.ArrayList;
import java.util.List;

import model.Sale;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleDAO {

    public boolean addSale(Sale sale) throws SQLException {

        String checkStock =
                "SELECT quantity FROM medicines WHERE medicine_id = ? FOR UPDATE";

        String updateStock =
                "UPDATE medicines SET quantity = quantity - ? WHERE medicine_id = ?";

        String insertSale =
                "INSERT INTO sales (medicine_id, sale_date, quantity, selling_price, total_amount) VALUES (?, ?, ?, ?, ?)";

        if (sale.getQuantity() <= 0 || sale.getSellingPrice() <= 0) {
            throw new IllegalArgumentException(
                    "Quantity and selling price must be greater than zero."
            );
        }

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {

                int availableStock;

                // Step 1: Check available medicine stock
                try (PreparedStatement ps = con.prepareStatement(checkStock)) {

                    ps.setInt(1, sale.getMedicineId());

                    try (ResultSet rs = ps.executeQuery()) {

                        if (!rs.next()) {
                            throw new SQLException("Medicine not found.");
                        }

                        availableStock = rs.getInt("quantity");
                    }
                }

                // Step 2: Validate stock
                if (availableStock < sale.getQuantity()) {
                    throw new SQLException("Insufficient medicine stock.");
                }

                // Step 3: Deduct medicine stock
                try (PreparedStatement ps = con.prepareStatement(updateStock)) {

                    ps.setInt(1, sale.getQuantity());
                    ps.setInt(2, sale.getMedicineId());

                    ps.executeUpdate();
                }

                // Step 4: Insert sales record
                try (PreparedStatement ps = con.prepareStatement(insertSale)) {

                    ps.setInt(1, sale.getMedicineId());
                    ps.setDate(2, sale.getSaleDate());
                    ps.setInt(3, sale.getQuantity());
                    ps.setDouble(4, sale.getSellingPrice());
                    ps.setDouble(5, sale.getTotalAmount());

                    ps.executeUpdate();
                }

                // Step 5: Commit transaction
                con.commit();

                return true;

            } catch (SQLException | RuntimeException e) {

                con.rollback();
                throw e;

            } finally {

                con.setAutoCommit(true);
            }
        }
    }

public List<Sale> getAllSales() throws SQLException {

    List<Sale> sales = new ArrayList<>();

    String sql = "SELECT * FROM sales ORDER BY sale_id DESC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {

            Sale sale = new Sale();

            sale.setSaleId(rs.getInt("sale_id"));
            sale.setMedicineId(rs.getInt("medicine_id"));
            sale.setSaleDate(rs.getDate("sale_date"));
            sale.setQuantity(rs.getInt("quantity"));
            sale.setSellingPrice(rs.getDouble("selling_price"));
            sale.setTotalAmount(rs.getDouble("total_amount"));

            sales.add(sale);
        }
    }

    return sales;
}

}

