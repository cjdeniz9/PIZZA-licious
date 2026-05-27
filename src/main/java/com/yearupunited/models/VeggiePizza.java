package com.yearupunited.models;

import com.yearupunited.models.enums.CrustType;
import com.yearupunited.models.enums.PizzaSize;
import com.yearupunited.models.enums.SauceType;
import com.yearupunited.models.enums.ToppingType;

public class VeggiePizza extends SignaturePizza {

    public VeggiePizza() {
        super("Veggie", PizzaSize.PERSONAL, CrustType.REGULAR, false);
        addSauce(SauceType.MARINARA);
        addTopping(new Topping("Bell Peppers", ToppingType.REGULAR));
        addTopping(new Topping("Spinach", ToppingType.REGULAR));
        addTopping(new Topping("Olives", ToppingType.REGULAR));
        addTopping(new Topping("Onions", ToppingType.REGULAR));
        addTopping(new Topping("Mozzarella", ToppingType.CHEESE));
    }
}
