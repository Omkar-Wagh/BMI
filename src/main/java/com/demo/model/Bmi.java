package com.demo.model;

public class Bmi {
    private double weight; // in kg
    private double height; // in feet
    private double bmiValue;
    private String category;

    public Bmi(double weight, double height) {
        this.weight = weight;
        this.height = height * 0.3048;
        this.bmiValue = calculateBmi();
        this.category = getBmiCategory();
    }

    private double calculateBmi() {
        return weight / (height * height);
    }

    private String getBmiCategory() {
        if (bmiValue < 18.5) return "Underweight";
        if (bmiValue < 24.9) return "Normal weight";
        if (bmiValue < 29.9) return "Overweight";
        return "Obese";
    }

    // Getters & Setters

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.bmiValue = calculateBmi(); // Recalculate BMI
        this.category = getBmiCategory();
    }

    public double getHeight() {
        return height / 0.3048; // Convert back to feet for display
    }

    public void setHeight(double height) {
        this.height = height * 0.3048; // Convert feet to meters
        this.bmiValue = calculateBmi(); // Recalculate BMI
        this.category = getBmiCategory();
    }

    public double getBmiValue() {
        return bmiValue;
    }

    public String getCategory() {
        return category;
    }
}
