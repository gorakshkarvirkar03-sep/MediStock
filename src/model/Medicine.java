package model;

public class Medicine {

    private int medicineId;
    private String medicineName;
    private String manufacturer;
    private String category;
    private double price;
    private int quantity;
    private String expiryDate;

    public Medicine(int medicineId, String medicineName,
                    String manufacturer, String category,
                    double price, int quantity, String expiryDate) {

        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.manufacturer = manufacturer;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
