
import model.Purchase;
import dao.PurchaseDAO;
import java.sql.Date;

public class TestAddPurchase {

    public static void main(String[] args) {

        Purchase purchase = new Purchase(
            0,
            1,
            1,
            Date.valueOf("2026-11-16"),
            50,
            20.00
        );

        PurchaseDAO purchaseDAO = new PurchaseDAO();

        boolean result = purchaseDAO.addPurchase(purchase);

        if (result) {
            System.out.println("Purchase added successfully!");
        } else {
            System.out.println("Failed to add purchase.");
        }
    }
}
