
import model.Supplier;
import dao.SupplierDAO;

public class TestUpdateSupplier {

    public static void main(String[] args) {

        Supplier supplier = new Supplier(
            1,
            "ABC Medical Distributors Updated",
            "Rahul Patil",
            "9876543215",
            "abcupdated@example.com",
            "Pune, Maharashtra"
        );

        SupplierDAO supplierDAO = new SupplierDAO();

        boolean result = supplierDAO.updateSupplier(supplier);

        if (result) {
            System.out.println("Supplier updated successfully!");
        } else {
            System.out.println("Failed to update supplier.");
        }
    }
}
