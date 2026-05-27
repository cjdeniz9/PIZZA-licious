package com.yearupunited.models;

import com.yearupunited.models.interfaces.IMenuItem;

public class GarlicKnots implements IMenuItem {

    private static final double PRICE = 1.50;

    @Override
    public String getDescription() {
        return "Garlic Knots - $" + String.format("%.2f", PRICE);
    }

    @Override
    public double calculatePrice() {
        return PRICE;
    }
}
