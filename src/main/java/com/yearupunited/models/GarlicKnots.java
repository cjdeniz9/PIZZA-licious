package com.yearupunited.models;

import com.yearupunited.models.interfaces.IMenuItem;

public class GarlicKnots implements IMenuItem {

    private final int amount;

    public GarlicKnots(int amount) {
        this.amount = amount;
    }

    @Override
    public String getDescription() {
        return amount + " x Garlic Knots - $" + String.format("%.2f", calculatePrice());
    }

    @Override
    public double calculatePrice() {
        return amount * 1.5;
    }
}
