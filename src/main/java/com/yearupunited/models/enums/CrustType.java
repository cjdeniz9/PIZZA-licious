package com.yearupunited.models.enums;

public enum CrustType {

    THIN("Thin"),
    REGULAR("Regular"),
    THICK("Thick"),
    CAULIFLOWER("Cauliflower"),
    STUFFED("Stuffed");

    private final String label;

    CrustType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
