package com.yearupunited.models;

import com.yearupunited.models.enums.CrustType;
import com.yearupunited.models.enums.PizzaSize;
import com.yearupunited.models.enums.SauceType;

public class CustomPizza extends Pizza {

    public CustomPizza(PizzaSize size, CrustType crustType, SauceType sauceType, boolean isStuffedCrust) {
        super(size, crustType, sauceType, isStuffedCrust);
    }

}
