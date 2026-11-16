
import service.ReportService;

public class TestReports {

    public static void main(String[] args) {

        ReportService reportService = new ReportService();

        // 1. Total medicine stock
        int totalStock = reportService.getTotalStockQuantity();

        // 2. Total inventory value
        double inventoryValue =
                reportService.getTotalInventoryValue();

        // 3. Total sales revenue
        double salesRevenue =
                reportService.getTotalSalesRevenue();

        // 4. Total sales transactions
        int totalSales = reportService.getTotalSalesCount();

        System.out.println("================================");
        System.out.println("       MEDISTOCK REPORT");
        System.out.println("================================");

        System.out.println("Total Medicine Stock: "
                + totalStock + " units");

        System.out.printf("Total Inventory Value: Rs. %.2f%n",
                inventoryValue);

        System.out.printf("Total Sales Revenue: Rs. %.2f%n",
                salesRevenue);

        System.out.println("Total Sales Transactions: "
                + totalSales);

        System.out.println("================================");
        System.out.println("Report generated successfully!");
    }
}

