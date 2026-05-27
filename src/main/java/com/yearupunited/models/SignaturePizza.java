package com.yearupunited.models;

import com.yearupunited.models.enums.CrustType;
import com.yearupunited.models.enums.PizzaSize;
import com.yearupunited.models.enums.SauceType;

public abstract class SignaturePizza extends Pizza {

    protected String name;

    public SignaturePizza(String name, PizzaSize size, CrustType crustType, boolean isStuffedCrust) {
        super(size, crustType, isStuffedCrust);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
