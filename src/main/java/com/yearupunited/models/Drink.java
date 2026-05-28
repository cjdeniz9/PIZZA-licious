package com.yearupunited.models;

import com.yearupunited.models.enums.DrinkSize;
import com.yearupunited.models.interfaces.IMenuItem;

public class Drink implements IMenuItem {

    private final String flavor;
    private final DrinkSize drinkSize;


    public Drink(String flavor, DrinkSize drinkSize) {
        this.flavor = flavor;
        this.drinkSize = drinkSize;
    }

    @Override
    public String getDescription() {
        return drinkSize.getLabel() + " " + flavor;
    }

    @Override
    public double calculatePrice() {
        return drinkSize.getBasePrice();
    }
}
