
import model.Medicine;
import service.InventoryService;

import java.util.List;

public class TestInventory {

    public static void main(String[] args) {

        InventoryService inventoryService = new InventoryService();

        // 1. Display all medicines
        System.out.println("===== ALL MEDICINES =====");

        List<Medicine> inventory =
                inventoryService.getAllInventory();

        for (Medicine medicine : inventory) {
            System.out.println(
                medicine.getMedicineId() + " | " +
                medicine.getMedicineName() + " | " +
                medicine.getManufacturer() + " | " +
                medicine.getQuantity()
            );
        }

        // 2. Display low-stock medicines
        System.out.println("\n===== LOW STOCK MEDICINES =====");

        List<Medicine> lowStock =
                inventoryService.getLowStockMedicines(10);

        for (Medicine medicine : lowStock) {
            System.out.println(
                medicine.getMedicineName() +
                " | Quantity: " + medicine.getQuantity()
            );
        }

        // 3. Display expired or soon-to-expire medicines
        System.out.println("\n===== EXPIRY ALERT =====");

        List<Medicine> expiring =
                inventoryService.getExpiringMedicines(30);

        for (Medicine medicine : expiring) {
            System.out.println(
                medicine.getMedicineName() +
                " | Expiry Date: " + medicine.getExpiryDate()
            );
        }

        System.out.println("\nInventory test completed.");
    }
}

