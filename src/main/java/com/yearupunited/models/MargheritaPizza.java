package com.yearupunited.models;

import com.yearupunited.models.enums.CrustType;
import com.yearupunited.models.enums.PizzaSize;
import com.yearupunited.models.enums.SauceType;
import com.yearupunited.models.enums.ToppingType;

public class MargheritaPizza extends SignaturePizza {

    public MargheritaPizza() {
        super("Margherita", PizzaSize.MEDIUM, CrustType.REGULAR, false);
        addSauce(SauceType.MARINARA);
        addSauce(SauceType.OLIVE_OIL);
        addTopping(new Topping("Mozzarella", ToppingType.CHEESE));
        addTopping(new Topping("Tomatoes", ToppingType.REGULAR));
        addTopping(new Topping("Basil", ToppingType.REGULAR));
    }

}
