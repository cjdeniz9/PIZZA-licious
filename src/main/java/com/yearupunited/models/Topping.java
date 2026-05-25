package com.yearupunited.models;

import com.yearupunited.models.enums.PizzaSize;
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

    public double getPrice(PizzaSize size) {
        return toppingType.getToppingPrice(size);
    }
}
