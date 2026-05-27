package com.yearupunited.ui.enums;

import java.util.Arrays;
import java.util.Optional;

public enum HomeMenuOption {
    NEW_ORDER(1, "New order"),
    EXIT(0, "Exit");

    private final int code;
    private final String label;

    HomeMenuOption(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static Optional<HomeMenuOption> fromCode(int code) {
        return Arrays.stream(values()).filter(option -> option.code == code).findFirst();
    }
}
