import dao.MedicineDAO;
import model.Medicine;

public class TestAddMedicine {

    public static void main(String[] args) {

        MedicineDAO dao = new MedicineDAO();

        Medicine medicine = new Medicine(
            0,
            "Dolo 650",
            "Micro Labs",
            "Tablet",
            30.00,
            100,
            "2028-12-31"
        );

        boolean result = dao.addMedicine(medicine);

        if (result) {
            System.out.println("Medicine added successfully!");
        } else {
            System.out.println("Failed to add medicine.");
        }
    }
}
