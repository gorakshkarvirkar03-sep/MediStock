
import service.MedicineService;
import model.Medicine;
import java.util.List;

public class TestMedicineService {

    public static void main(String[] args) {

        MedicineService service = new MedicineService();

        List<Medicine> medicines = service.getAllMedicines();

        System.out.println("===== MEDISTOCK SERVICE TEST =====");

        for (Medicine medicine : medicines) {
            System.out.println(
                medicine.getMedicineId() + " - " +
                medicine.getMedicineName()
            );
        }

        System.out.println("Total medicines: " + medicines.size());
        System.out.println("Service is working!");
    }
}
