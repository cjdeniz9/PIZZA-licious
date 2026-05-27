package com.yearupunited.models;

import com.yearupunited.models.enums.CrustType;
import com.yearupunited.models.enums.PizzaSize;
import com.yearupunited.models.enums.ToppingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomPizzaTest {

    @Test
    void pizzaWithNoToppingsShouldEqualBasePriceOfTwelve() {
        CustomPizza p1 = new CustomPizza(PizzaSize.MEDIUM, CrustType.REGULAR, false);

        assertEquals(12.00, p1.calculatePrice());
    }

    @Test
    void pizzaWithMeatToppingShouldBePricedCorrectlyByBasePrice() {
        CustomPizza p1 = new CustomPizza(PizzaSize.MEDIUM, CrustType.REGULAR, false);

        Topping m1 = new Topping("Pepperoni", ToppingType.MEAT);

        p1.addTopping(m1);

        assertEquals(14.00, p1.calculatePrice());
    }

    @Test
    void pizzaWithMultipleToppingsShouldBePricedCorrectly() {
        CustomPizza p1 = new CustomPizza(PizzaSize.MEDIUM, CrustType.REGULAR, false);

        Topping m1 = new Topping("Pepperoni", ToppingType.MEAT);
        Topping c1 = new Topping("Mozzarella", ToppingType.CHEESE);

        p1.addTopping(m1);
        p1.addTopping(c1);

        assertEquals(15.50, p1.calculatePrice());
    }
}