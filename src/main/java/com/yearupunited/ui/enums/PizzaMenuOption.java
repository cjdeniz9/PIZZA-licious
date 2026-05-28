package com.yearupunited.ui.enums;

import com.yearupunited.models.interfaces.ILabelled;

import java.util.Arrays;
import java.util.Optional;

public enum PizzaMenuOption implements ILabelled {
    SIGNATURE(1, "Signature pizza"),
    CUSTOM(2, "Custom pizza");

    private final int code;
    private final String label;

    PizzaMenuOption(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static Optional<PizzaMenuOption> fromCode(int code) {
        return Arrays.stream(values()).filter(option -> option.code == code).findFirst();
    }
}
