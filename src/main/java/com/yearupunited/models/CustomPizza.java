package com.yearupunited.models;

import com.yearupunited.models.enums.CrustType;
import com.yearupunited.models.enums.PizzaSize;
import com.yearupunited.models.enums.SauceType;

public class CustomPizza extends Pizza {

    public CustomPizza(PizzaSize size, CrustType crustType, boolean isStuffedCrust) {
        super(size, crustType, isStuffedCrust);
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
