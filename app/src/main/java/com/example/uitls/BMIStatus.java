package com.example.uitls;

public class BMIStatus {
    private double BMI;
    private String description;

    public BMIStatus(double BMI, String description) {
        this.BMI = BMI;
        this.description = description;
    }

    public BMIStatus() {
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
