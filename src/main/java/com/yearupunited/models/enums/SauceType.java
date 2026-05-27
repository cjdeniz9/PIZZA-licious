package com.yearupunited.models.enums;

import com.yearupunited.models.interfaces.Labelled;

public enum SauceType implements Labelled {

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
