
import dao.SupplierDAO;
import model.Supplier;
import java.util.List;

public class TestSupplier {

    public static void main(String[] args) {

        SupplierDAO supplierDAO = new SupplierDAO();

        List<Supplier> suppliers = supplierDAO.getAllSuppliers();

        System.out.println("========== SUPPLIER LIST ==========");

        for (Supplier supplier : suppliers) {

            System.out.println("Supplier ID: " + supplier.getSupplierId());
            System.out.println("Name: " + supplier.getSupplierName());
            System.out.println("Contact Person: " + supplier.getContactPerson());
            System.out.println("Phone: " + supplier.getPhone());
            System.out.println("Email: " + supplier.getEmail());
            System.out.println("Address: " + supplier.getAddress());

            System.out.println("----------------------------------");
        }

        System.out.println("Total Suppliers: " + suppliers.size());
    }
}
