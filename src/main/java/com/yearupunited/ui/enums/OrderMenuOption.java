package com.yearupunited.ui.enums;

import java.util.Arrays;
import java.util.Optional;

public enum OrderMenuOption {
    ADD_PIZZA(1, "Add pizza"),
    ADD_DRINK(2, "Add drink"),
    ADD_GARLIC_KNOTS(3, "Add garlic knots"),
    CHECKOUT(4, "Checkout"),
    CANCEL_ORDER(0, "Cancel order");

    private final int code;
    private final String label;

    OrderMenuOption(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static Optional<OrderMenuOption> fromCode(int code) {
        return Arrays.stream(values()).filter(option -> option.code == code).findFirst();
    }
}
