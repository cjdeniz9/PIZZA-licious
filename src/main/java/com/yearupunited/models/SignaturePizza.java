package com.yearupunited.models;

import com.yearupunited.models.enums.CrustType;
import com.yearupunited.models.enums.PizzaSize;
import com.yearupunited.models.enums.SauceType;

public abstract class SignaturePizza extends Pizza {

    protected String name;

    public SignaturePizza(String name, PizzaSize size, CrustType crustType, SauceType sauceType, boolean isStuffedCrust) {
        super(size, crustType, sauceType, isStuffedCrust);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
