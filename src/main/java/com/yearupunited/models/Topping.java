package com.yearupunited.models;

import com.yearupunited.models.enums.ToppingType;

public class Topping {

    private final String name;
    private final ToppingType toppingType;

    public Topping(String name, ToppingType toppingType) {
        this.name = name;
        this.toppingType = toppingType;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return toppingType;
    }

    @Override
    public String toString() {
        return String.format("%s : %s", name, toppingType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Topping topping = (Topping) o;

        return name.equalsIgnoreCase(topping.name)
                && toppingType == topping.toppingType;
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode() + toppingType.hashCode();
    }
}