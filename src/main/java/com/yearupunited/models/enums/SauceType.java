package com.yearupunited.models.enums;

import com.yearupunited.models.interfaces.ILabelled;

public enum SauceType implements ILabelled {

    MARINARA("Marinara"),
    ALFREDO("Alfredo"),
    PESTO("Pesto"),
    BBQ("BBQ"),
    BUFFALO("Buffalo"),
    OLIVE_OIL("Olive oil");

    private final String label;

    SauceType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }


}
