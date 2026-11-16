
import model.Sale;
import service.SaleService;

import java.sql.Date;

public class TestInsufficientStock {

    public static void main(String[] args) {

        Sale sale = new Sale(
            1,
            Date.valueOf("2026-11-16"),
            10000,
            30.00
        );

        SaleService saleService = new SaleService();

        boolean result = saleService.processSale(sale);

        if (result) {
            System.out.println("Unexpected: Sale was completed!");
        } else {
            System.out.println("Insufficient stock test passed!");
            System.out.println("Sale was rejected successfully.");
        }
    }
}
