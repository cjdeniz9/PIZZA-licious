package com.yearupunited.models;

import com.yearupunited.models.enums.CrustType;
import com.yearupunited.models.enums.PizzaSize;
import com.yearupunited.models.enums.SauceType;
import com.yearupunited.models.enums.ToppingType;
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
        // removeIf loops through the toppings list and removes any item where the condition is true
        toppings.removeIf(t ->
                t.getType() == topping.getType()
                        && t.getName().equalsIgnoreCase(topping.getName())
        );
    }

    @Override
    public String getDescription() {
        String toppingList = toppings.isEmpty() ? "None" : toppings.stream().map(Topping::getName).collect(Collectors.joining(", "));
        String sauceList = sauces.isEmpty() ? "None" : sauces.stream().map(SauceType::getLabel).collect(Collectors.joining(", "));

        return size.getLabel() + " Pizza" +
                "\nCrust: " + crustType.getLabel() +
                "\nSauce: " + sauceList +
                "\nToppings: " + toppingList + "\nStuffed Crust: " + (isStuffedCrust ? "Yes" : "No");
    }

    @Override
    public double calculatePrice() {
        double toppingsTotal = 0.0;

        long meatCount = toppings.stream()
                .filter(t -> t.getType() == ToppingType.MEAT)
                .count();

        long cheeseCount = toppings.stream()
                .filter(t -> t.getType() == ToppingType.CHEESE)
                .count();

        if (meatCount >= 1) {
            toppingsTotal += ToppingType.MEAT.getToppingPrice(size);

            if (meatCount > 1) {
                double extraMeat =
                        switch (size) {
                            case PERSONAL -> 0.50;
                            case MEDIUM -> 1.00;
                            case LARGE -> 1.50;
                        };
                toppingsTotal += (meatCount - 1) * extraMeat;
            }
        }

        if (cheeseCount >= 1) {
            toppingsTotal += ToppingType.CHEESE.getToppingPrice(size);

            if (cheeseCount > 1) {
                double extraCheese =
                        switch (size) {
                            case PERSONAL -> 0.30;
                            case MEDIUM -> 0.60;
                            case LARGE -> 0.80;
                        };
                toppingsTotal += (cheeseCount - 1) * extraCheese;
            }
        }

        return size.getBasePrice() + toppingsTotal;
    }

}
