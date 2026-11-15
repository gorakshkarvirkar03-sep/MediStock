
import java.util.List;

import dao.MedicineDAO;
import model.Medicine;

public class TestMedicine {

    public static void main(String[] args) {

        MedicineDAO dao = new MedicineDAO();

        List<Medicine> medicines = dao.getAllMedicines();

        System.out.println("========== MEDISTOCK MEDICINES ==========");

        for (Medicine medicine : medicines) {

            System.out.println("ID: " + medicine.getMedicineId());
            System.out.println("Name: " + medicine.getMedicineName());
            System.out.println("Manufacturer: " + medicine.getManufacturer());
            System.out.println("Category: " + medicine.getCategory());
            System.out.println("Price: " + medicine.getPrice());
            System.out.println("Quantity: " + medicine.getQuantity());
            System.out.println("Expiry Date: " + medicine.getExpiryDate());

            System.out.println("-----------------------------------------");
        }

        System.out.println("Total medicines: " + medicines.size());
    }
}
