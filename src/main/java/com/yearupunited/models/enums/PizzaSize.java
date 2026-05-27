package com.yearupunited.models.enums;

import com.yearupunited.models.interfaces.Labelled;

public enum PizzaSize implements Labelled {

    PERSONAL("Personal 8\"", 8.50),
    MEDIUM("Medium 12\"", 12.00),
    LARGE("Large 16\"", 16.50);

    private final String label;
    private final double basePrice;

    PizzaSize(String label, double basePrice) {
        this.label = label;
        this.basePrice = basePrice;
    }

    public String getLabel() {
        return label;
    }

    public double getBasePrice() {
        return basePrice;
    }

}
