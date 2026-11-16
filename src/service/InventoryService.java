
package service;

import dao.InventoryDAO;
import model.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private InventoryDAO inventoryDAO;

    public InventoryService() {
        inventoryDAO = new InventoryDAO();
    }

    // View all medicines in inventory
    public List<Medicine> getAllInventory() {
        try {
            return inventoryDAO.getAllInventory();
        } catch (SQLException e) {
            System.out.println("Inventory error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // View medicines with low stock
    public List<Medicine> getLowStockMedicines(int threshold) {
        try {
            return inventoryDAO.getLowStockMedicines(threshold);
        } catch (SQLException e) {
            System.out.println("Low stock error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // View expired or soon-to-expire medicines
    public List<Medicine> getExpiringMedicines(int daysAhead) {
        try {
            return inventoryDAO.getExpiringMedicines(daysAhead);
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Expiry check error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

