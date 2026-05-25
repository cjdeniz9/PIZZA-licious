package com.yearupunited.models;

import com.yearupunited.models.enums.CrustType;
import com.yearupunited.models.enums.PizzaSize;
import com.yearupunited.models.enums.SauceType;
import com.yearupunited.models.interfaces.IMenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Pizza implements IMenuItem {

    protected PizzaSize size;
    protected CrustType crustType;
    protected SauceType sauceType;
    protected List<Topping> toppings;
    protected boolean isStuffedCrust;

    public Pizza(PizzaSize size, CrustType crustType, SauceType sauceType, boolean isStuffedCrust) {
        this.size = size;
        this.crustType = crustType;
        this.sauceType = sauceType;
        this.isStuffedCrust = isStuffedCrust;
        this.toppings = new ArrayList<>();
    }

    public PizzaSize getSize() {
        return size;
    }

    public CrustType getCrustType() {
        return crustType;
    }

    public SauceType getSauceType() {
        return sauceType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public boolean isStuffedCrust() {
        return isStuffedCrust;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public String getDescription() {
        String toppingList = toppings.isEmpty() ? "None" : toppings.stream().map(Topping::getName).collect(Collectors.joining(", "));

        return size.getLabel() + " Pizza" +
                "\n Crust: " + crustType.getLabel() +
                "\n Sauce: " + sauceType.getLabel() +
                "\n Toppings: " + toppingList +
                "\n Stuffed Crust: " + (isStuffedCrust ? "Yes" : "No");
    }

    public double calculatePrice() {
        double toppingsTotal = 0.0;

        for (Topping topping : toppings) {
            toppingsTotal += topping.getPrice(size);

        }

        return size.getBasePrice() + toppingsTotal;
    }

}
