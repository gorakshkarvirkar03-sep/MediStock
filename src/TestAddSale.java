
import model.Sale;
import service.SaleService;

import java.sql.Date;

public class TestAddSale {

    public static void main(String[] args) {

        Sale sale = new Sale(
            1,
            Date.valueOf("2026-11-16"),
            5,
            30.00
        );

        SaleService saleService = new SaleService();

        boolean result = saleService.processSale(sale);

        if (result) {
            System.out.println("Sale completed successfully!");
            System.out.println("Medicine ID: " + sale.getMedicineId());
            System.out.println("Quantity Sold: " + sale.getQuantity());
            System.out.println("Selling Price: " + sale.getSellingPrice());
            System.out.println("Total Amount: " + sale.getTotalAmount());
            System.out.println("Medicine stock updated successfully!");
        } else {
            System.out.println("Sale could not be completed.");
        }
    }
}


