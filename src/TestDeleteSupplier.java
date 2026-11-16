
import dao.SupplierDAO;

public class TestDeleteSupplier {

    public static void main(String[] args) {

        int supplierId = 4;

        SupplierDAO supplierDAO = new SupplierDAO();

        boolean result = supplierDAO.deleteSupplier(supplierId);

        if (result) {
            System.out.println("Supplier deleted successfully!");
        } else {
            System.out.println("Failed to delete supplier.");
        }
    }
}
