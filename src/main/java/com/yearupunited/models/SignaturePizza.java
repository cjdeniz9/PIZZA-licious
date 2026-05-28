package com.yearupunited.models;

import com.yearupunited.models.enums.CrustType;
import com.yearupunited.models.enums.PizzaSize;
import com.yearupunited.models.enums.SauceType;

import java.util.stream.Collectors;

public abstract class SignaturePizza extends Pizza {

    protected String name;

    public SignaturePizza(String name, PizzaSize size, CrustType crustType, boolean isStuffedCrust) {
        super(size, crustType, isStuffedCrust);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Signature Pizza: " + getName() + "\n" +
                "Size: " + getSize().getLabel() + "\n" +
                "Crust: " + getCrustType().getLabel() + "\n" +
                "Stuffed Crust: " + (isStuffedCrust() ? "Yes" : "No") + "\n" +
                "Sauces: " + getSauces().stream().map(SauceType::getLabel).collect(Collectors.joining(", ")) + "\n" +
                "Toppings: " + getToppings().stream().map(Topping::getName).collect(Collectors.joining(", "));
    }

}
