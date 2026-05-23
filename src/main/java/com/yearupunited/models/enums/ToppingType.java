package com.yearupunited.models.enums;

public enum ToppingType {

    MEAT("Meat", 1.00, 2.00, 3.00),
    CHEESE("Cheese", 0.75, 1.50, 2.25),
    REGULAR("Regular", 0.00, 0.00, 0.00);

    private final String label;
    private final double personalSizePricing;
    private final double mediumSizePricing;
    private final double largeSizePricing;

    ToppingType(String label, double personalSizePricing, double mediumSizePricing, double largeSizePricing) {
        this.label = label;
        this.personalSizePricing = personalSizePricing;
        this.mediumSizePricing = mediumSizePricing;
        this.largeSizePricing = largeSizePricing;
    }

    public String getLabel() {
        return label;
    }

    public double getPersonalSizePricing() {
        return personalSizePricing;
    }

    public double getMediumSizePricing() {
        return mediumSizePricing;
    }

    public double getLargeSizePricing() {
        return largeSizePricing;
    }
}
