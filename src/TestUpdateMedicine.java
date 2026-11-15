import dao.MedicineDAO;

public class TestUpdateMedicine {

    public static void main(String[] args) {

        MedicineDAO dao = new MedicineDAO();

        // Update Paracetamol (ID = 1)
        boolean result = dao.updateMedicine(1, 28.00, 180);

        if (result) {
            System.out.println("Medicine updated successfully!");
        } else {
            System.out.println("Medicine update failed!");
        }
    }
}
