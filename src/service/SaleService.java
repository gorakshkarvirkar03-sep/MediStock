
package service;

import dao.SaleDAO;
import model.Sale;

import java.sql.SQLException;
import java.util.List;

public class SaleService {

    private SaleDAO saleDAO;

    public SaleService() {
        this.saleDAO = new SaleDAO();
    }

    public boolean processSale(Sale sale) {

        try {

            if (sale == null) {
                throw new IllegalArgumentException("Sale details cannot be empty.");
            }

            if (sale.getMedicineId() <= 0) {
                throw new IllegalArgumentException("Invalid medicine ID.");
            }

            if (sale.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero.");
            }

            if (sale.getSellingPrice() <= 0) {
                throw new IllegalArgumentException("Selling price must be greater than zero.");
            }

            return saleDAO.addSale(sale);

        } catch (IllegalArgumentException | SQLException e) {

            System.out.println("Sale failed: " + e.getMessage());
            return false;
        }
    }
    public List<Sale> getAllSales() {

    try {
        return saleDAO.getAllSales();

    } catch (SQLException e) {

        System.out.println("Unable to retrieve sales: " + e.getMessage());
        return new java.util.ArrayList<>();
    }
}
}


