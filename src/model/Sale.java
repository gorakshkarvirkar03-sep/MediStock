
package model;

import java.sql.Date;

public class Sale {

    private int saleId;
    private int medicineId;
    private Date saleDate;
    private int quantity;
    private double sellingPrice;
    private double totalAmount;

    public Sale() {
    }

    public Sale(int medicineId, Date saleDate, int quantity,
                double sellingPrice) {
        this.medicineId = medicineId;
        this.saleDate = saleDate;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.totalAmount = quantity * sellingPrice;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
