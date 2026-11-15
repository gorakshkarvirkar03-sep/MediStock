
import model.Supplier;
import dao.SupplierDAO;

public class TestAddSupplier {

    public static void main(String[] args) {

        // Create Supplier object
        Supplier supplier = new Supplier(
            0,
            "Demo Pharma Distributors",
            "Rahul Patil",
            "9876543210",
            "demo@gmail.com",
            "Pune, Maharashtra"
        );

        // Create DAO object
        SupplierDAO supplierDAO = new SupplierDAO();

        // Insert supplier into database
        boolean result = supplierDAO.addSupplier(supplier);

        if (result) {
            System.out.println("Supplier added successfully!");
        } else {
            System.out.println("Failed to add supplier.");
        }
    }
}
