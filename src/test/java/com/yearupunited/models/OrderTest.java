package com.yearupunited.models;

import com.yearupunited.models.enums.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void orderShouldEqualNineteenFifty() {
        Order order = new Order();

        CustomPizza customPizza = new CustomPizza(PizzaSize.MEDIUM, CrustType.THICK, false);
        Topping meat = new Topping("Pepperoni", ToppingType.MEAT);
        Topping cheese = new Topping("Mozzarella", ToppingType.CHEESE);
        Topping regular = new Topping("Mushrooms", ToppingType.REGULAR);
        SauceType sauce = SauceType.MARINARA;

        Drink drink = new Drink("Coke Zero", DrinkSize.MEDIUM);

        GarlicKnots garlicKnots = new GarlicKnots();

        customPizza.addTopping(meat);
        customPizza.addTopping(cheese);
        customPizza.addTopping(regular);
        customPizza.addSauce(sauce);

        order.addItem(customPizza);
        order.addItem(drink);
        order.addItem(garlicKnots);

        assertEquals(19.5, order.getTotal());
    }

    @Test
    void orderShouldEqualZeroWhenEmpty() {
        Order order = new Order();

        assertEquals(0.0, order.getTotal());
    }

    @Test
    void orderShouldEqualTwentyOneSeventyFive() {
        Order order = new Order();

        CustomPizza p1 = new CustomPizza(PizzaSize.LARGE, CrustType.THICK, true);
        Topping m1 = new Topping("Sausage", ToppingType.MEAT);
        Topping c1 = new Topping("Mozzarella", ToppingType.CHEESE);
        Topping r1 = new Topping("Mushrooms", ToppingType.REGULAR);
        SauceType s1 = SauceType.MARINARA;

        p1.addTopping(m1);
        p1.addTopping(c1);
        p1.addTopping(r1);
        p1.addSauce(s1);

        order.addItem(p1);

        assertEquals(21.75, order.getTotal());
    }

    @Test
    void orderShouldEqualFifteenFifty() {
        Order order = new Order();

        CustomPizza p2 = new CustomPizza(PizzaSize.MEDIUM, CrustType.THICK, false);
        Topping m2 = new Topping("Pepperoni", ToppingType.MEAT);
        Topping c2 = new Topping("Mozzarella", ToppingType.CHEESE);
        Topping r2 = new Topping("Mushrooms", ToppingType.REGULAR);
        SauceType s2 = SauceType.MARINARA;

        p2.addTopping(m2);
        p2.addTopping(c2);
        p2.addTopping(r2);
        p2.addSauce(s2);

        order.addItem(p2);

        assertEquals(15.5, order.getTotal());
    }

    @Test
    void orderShouldEqualFortyOneTwentyFive() {
        Order order = new Order();

        CustomPizza p1 = new CustomPizza(PizzaSize.LARGE, CrustType.THICK, true);
        Topping m1 = new Topping("Sausage", ToppingType.MEAT);
        Topping c1 = new Topping("Mozzarella", ToppingType.CHEESE);
        Topping r1 = new Topping("Mushrooms", ToppingType.REGULAR);
        SauceType s1 = SauceType.MARINARA;

        CustomPizza p2 = new CustomPizza(PizzaSize.MEDIUM, CrustType.THICK, false);
        Topping m2 = new Topping("Pepperoni", ToppingType.MEAT);
        Topping c2 = new Topping("Mozzarella", ToppingType.CHEESE);
        Topping r2 = new Topping("Mushrooms", ToppingType.REGULAR);
        SauceType s2 = SauceType.MARINARA;

        p1.addTopping(m1);
        p1.addTopping(c1);
        p1.addTopping(r1);
        p1.addSauce(s1);

        p2.addTopping(m2);
        p2.addTopping(c2);
        p2.addTopping(r2);
        p2.addSauce(s2);

        order.addItem(p1);
        order.addItem(p2);

        assertEquals(37.25, order.getTotal());
    }

    @Test
    void shouldPlaceOrderIfPizzaIsPresent() {
        Order order = new Order();

        CustomPizza p1 = new CustomPizza(PizzaSize.LARGE, CrustType.THICK, true);
        Topping m1 = new Topping("Sausage", ToppingType.MEAT);
        Topping c1 = new Topping("Mozzarella", ToppingType.CHEESE);
        Topping r1 = new Topping("Mushrooms", ToppingType.REGULAR);
        SauceType s1 = SauceType.MARINARA;

        p1.addTopping(m1);
        p1.addTopping(c1);
        p1.addTopping(r1);
        p1.addSauce(s1);

        order.addItem(p1);
        order.placeOrder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String fileName = LocalDateTime.now().format(formatter) + ".txt";
        String filePath = "receipts/" + fileName;

        assertTrue(Files.exists(Path.of(filePath)));
    }

    @Test
    void shouldPlaceOrderIfDrinkIsPresent() {
        Order order = new Order();

        Drink drink = new Drink("Coke Zero", DrinkSize.MEDIUM);

        order.addItem(drink);
        order.placeOrder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String fileName = LocalDateTime.now().format(formatter) + ".txt";
        String filePath = "receipts/" + fileName;

        assertTrue(Files.exists(Path.of(filePath)));

        try {
            Thread.sleep(3000); // 3000 milliseconds = 3 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void orderShouldNotBePlacedWhenEmpty() {
        Order order = new Order();

        assertFalse(order.placeOrder());
    }

}