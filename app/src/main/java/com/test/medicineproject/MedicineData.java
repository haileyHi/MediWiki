package com.test.medicineproject;

public class MedicineData {
    int medicineIdx;
    String medicineTitle;
    String medicineCompany;
    String medicineImage;
    String medicineEfcy, medicineMethod, medicineWarn, atpnQesitm, intrcQesitm, seQesitm, depositMethodQesitm;

    public MedicineData(String medicineTitle, String medicineCompany, String medicineImage) {
        this.medicineTitle = medicineTitle;
        this.medicineCompany = medicineCompany;
        this.medicineImage = medicineImage;
    }

    public MedicineData(String medicineTitle, String medicineCompany, String medicineImage, String medicineEfcy, String medicineMethod, String medicineWarn, String atpnQesitm, String intrcQesitm, String seQesitm, String depositMethodQesitm) {
        this.medicineTitle = medicineTitle;
        this.medicineCompany = medicineCompany;
        this.medicineImage = medicineImage;
        this.medicineEfcy = medicineEfcy;
        this.medicineMethod = medicineMethod;
        this.medicineWarn = medicineWarn;
        this.atpnQesitm = atpnQesitm;
        this.intrcQesitm = intrcQesitm;
        this.seQesitm = seQesitm;
        this.depositMethodQesitm = depositMethodQesitm;
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

    public String getMedicineEfcy() {
        return medicineEfcy;
    }

    public void setMedicineEfcy(String medicineEfcy) {
        this.medicineEfcy = medicineEfcy;
    }

    public String getMedicineMethod() {
        return medicineMethod;
    }

    public void setMedicineMethod(String medicineMethod) {
        this.medicineMethod = medicineMethod;
    }

    public String getMedicineWarn() {
        return medicineWarn;
    }

    public void setMedicineWarn(String medicineWarn) {
        this.medicineWarn = medicineWarn;
    }

    public String getAtpnQesitm() {
        return atpnQesitm;
    }

    public void setAtpnQesitm(String atpnQesitm) {
        this.atpnQesitm = atpnQesitm;
    }

    public String getIntrcQesitm() {
        return intrcQesitm;
    }

    public void setIntrcQesitm(String intrcQesitm) {
        this.intrcQesitm = intrcQesitm;
    }

    public String getSeQesitm() {
        return seQesitm;
    }

    public void setSeQesitm(String seQesitm) {
        this.seQesitm = seQesitm;
    }

    public String getDepositMethodQesitm() {
        return depositMethodQesitm;
    }

    public void setDepositMethodQesitm(String depositMethodQesitm) {
        this.depositMethodQesitm = depositMethodQesitm;
    }
}
