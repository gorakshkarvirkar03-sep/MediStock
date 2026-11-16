
import model.Sale;
import service.SaleService;

import java.util.List;

public class TestViewSales {

    public static void main(String[] args) {

        SaleService saleService = new SaleService();

        List<Sale> sales = saleService.getAllSales();

        if (sales.isEmpty()) {
            System.out.println("No sales records found.");
            return;
        }

        System.out.println("========== SALES HISTORY ==========");

        for (Sale sale : sales) {

            System.out.println("Sale ID: " + sale.getSaleId());
            System.out.println("Medicine ID: " + sale.getMedicineId());
            System.out.println("Sale Date: " + sale.getSaleDate());
            System.out.println("Quantity: " + sale.getQuantity());
            System.out.println("Selling Price: " + sale.getSellingPrice());
            System.out.println("Total Amount: " + sale.getTotalAmount());

            System.out.println("-----------------------------------");
        }

        System.out.println("Total Sales Records: " + sales.size());
    }
}

