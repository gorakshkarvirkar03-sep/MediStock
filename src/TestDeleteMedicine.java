import dao.MedicineDAO;

public class TestDeleteMedicine {

    public static void main(String[] args) {

        MedicineDAO dao = new MedicineDAO();

        // Delete Dolo 650 (ID = 6)
        boolean result = dao.deleteMedicine(6);

        if (result) {
            System.out.println("Medicine deleted successfully!");
        } else {
            System.out.println("Medicine not found or deletion failed!");
        }
    }
}
