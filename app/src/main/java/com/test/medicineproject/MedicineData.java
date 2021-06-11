package com.test.medicineproject;

public class MedicineData {
    int medicineIdx;
    String medicineTitle;
    String medicineCompany;
    String medicineImage;

    public MedicineData(String medicineTitle, String medicineCompany, String medicineImage) {
        this.medicineTitle = medicineTitle;
        this.medicineCompany = medicineCompany;
        this.medicineImage = medicineImage;
    }

    public String getMedicineTitle() {
        return medicineTitle;
    }

    public void setMedicineTitle(String medicineTitle) {
        this.medicineTitle = medicineTitle;
    }

    public String getMedicineCompany() {
        return medicineCompany;
    }

    public void setMedicineCompany(String medicineCompany) {
        this.medicineCompany = medicineCompany;
    }

    public String getMedicineImage() {
        return medicineImage;
    }

    public void setMedicineImage(String medicineImage) {
        this.medicineImage = medicineImage;
    }
}
