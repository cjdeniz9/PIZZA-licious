package com.yearupunited.models.enums;

import com.yearupunited.models.interfaces.ILabelled;

public enum CrustType implements ILabelled {

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
