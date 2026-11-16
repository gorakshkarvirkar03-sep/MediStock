
package service;

import dao.ReportDAO;

import java.sql.SQLException;

public class ReportService {

    private ReportDAO reportDAO;

    public ReportService() {
        reportDAO = new ReportDAO();
    }

    // Get total medicine stock
    public int getTotalStockQuantity() {
        try {
            return reportDAO.getTotalStockQuantity();
        } catch (SQLException e) {
            System.out.println("Stock report error: " + e.getMessage());
            return 0;
        }
    }

    // Get total inventory value
    public double getTotalInventoryValue() {
        try {
            return reportDAO.getTotalInventoryValue();
        } catch (SQLException e) {
            System.out.println("Inventory report error: " + e.getMessage());
            return 0.0;
        }
    }

    // Get total sales revenue
    public double getTotalSalesRevenue() {
        try {
            return reportDAO.getTotalSalesRevenue();
        } catch (SQLException e) {
            System.out.println("Sales report error: " + e.getMessage());
            return 0.0;
        }
    }

    // Get total sales transactions
    public int getTotalSalesCount() {
        try {
            return reportDAO.getTotalSalesCount();
        } catch (SQLException e) {
            System.out.println("Sales count error: " + e.getMessage());
            return 0;
        }
    }
}

