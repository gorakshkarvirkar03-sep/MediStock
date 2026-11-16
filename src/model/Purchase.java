
package model;

import java.sql.Date;

public class Purchase {

    private int purchaseId;
    private int medicineId;
    private int supplierId;
    private Date purchaseDate;
    private int quantity;
    private double purchasePrice;

    public Purchase(int purchaseId, int medicineId,
                    int supplierId, Date purchaseDate,
                    int quantity, double purchasePrice) {

        this.purchaseId = purchaseId;
        this.medicineId = medicineId;
        this.supplierId = supplierId;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }
}
