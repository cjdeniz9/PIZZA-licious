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
    protected List<SauceType> sauces = new ArrayList<>();
    protected List<Topping> toppings = new ArrayList<>();
    protected boolean isStuffedCrust;

    public Pizza(PizzaSize size, CrustType crustType, boolean isStuffedCrust) {
        this.size = size;
        this.crustType = crustType;
        this.isStuffedCrust = isStuffedCrust;
    }

    public PizzaSize getSize() {
        return size;
    }

    public CrustType getCrustType() {
        return crustType;
    }

    public List<SauceType> getSauces() {
        return sauces;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public boolean isStuffedCrust() {
        return isStuffedCrust;
    }

    public void addSauce(SauceType sauce) { sauces.add(sauce); }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    @Override
    public String getDescription() {
        String toppingList = toppings.isEmpty() ? "None" : toppings.stream().map(Topping::getName).collect(Collectors.joining(", "));
        String sauceList = sauces.isEmpty() ? "None" : sauces.stream().map(SauceType::getLabel).collect(Collectors.joining(", "));

        return size.getLabel() + " Pizza" +
                "\n Crust: " + crustType.getLabel() +
                "\n Sauce: " + sauceList +
                "\n Toppings: " + toppingList +
                "\n Stuffed Crust: " + (isStuffedCrust ? "Yes" : "No");
    }

    @Override
    public double calculatePrice() {
        double toppingsTotal = 0.0;

        for (Topping topping : toppings) {
            toppingsTotal += topping.getPrice(size);

        }

        return size.getBasePrice() + toppingsTotal;
    }

}
