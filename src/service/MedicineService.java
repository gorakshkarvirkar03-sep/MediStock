
package service;

import dao.MedicineDAO;
import model.Medicine;
import java.util.List;

public class MedicineService {

    private MedicineDAO medicineDAO = new MedicineDAO();

    // View all medicines
    public List<Medicine> getAllMedicines() {
        return medicineDAO.getAllMedicines();
    }

    // Add a medicine
    public boolean addMedicine(Medicine medicine) {

        if (medicine == null) {
            return false;
        }

        if (medicine.getMedicineName() == null ||
            medicine.getMedicineName().trim().isEmpty()) {
            return false;
        }

        if (medicine.getPrice() < 0 || medicine.getQuantity() < 0) {
            return false;
        }

        return medicineDAO.addMedicine(medicine);
    }

    // Update medicine
    public boolean updateMedicine(int id, double price, int quantity) {

        if (id <= 0 || price < 0 || quantity < 0) {
            return false;
        }

        return medicineDAO.updateMedicine(id, price, quantity);
    }

    // Delete medicine
    public boolean deleteMedicine(int id) {

        if (id <= 0) {
            return false;
        }

        return medicineDAO.deleteMedicine(id);
    }
}
