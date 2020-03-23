package com.example.sqllite;

public class SinhVien {
    private String masv;
    private String name;
    private double dtb;

    public SinhVien(String masv, String name, double dtb) {
        this.masv = masv;
        this.name = name;
        this.dtb = dtb;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDtb() {
        return dtb;
    }

    public void setDtb(double dtb) {
        this.dtb = dtb;
    }
}
