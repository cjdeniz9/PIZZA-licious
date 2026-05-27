package com.yearupunited.ui;

import com.yearupunited.models.*;
import com.yearupunited.models.enums.*;
import com.yearupunited.models.interfaces.Labelled;
import com.yearupunited.models.services.ToppingManager;
import com.yearupunited.ui.enums.HomeMenuOption;
import com.yearupunited.ui.enums.OrderMenuOption;

import java.util.ArrayList;
import java.util.List;

import static com.yearupunited.ui.Helper.*;
import static com.yearupunited.ui.ConsoleStyle.*;

public class UserInterface {

    public void displayHome() throws InterruptedException{
        ToppingManager.loadToppings("src/main/resources/toppings.txt");

        displayHeader();

        HomeMenuOption selectedOption;
        do {
            displayHomeMenu();
            int choice = readInt(GOLD + "  >> " + WHITE);
            selectedOption = HomeMenuOption.fromCode(choice).orElse(null);
            handleHomeMenu(selectedOption);
        } while (selectedOption != HomeMenuOption.EXIT);

        System.out.println();
        System.out.println("Thank you for visiting PIZZA-licious. Goodbye!");
        System.out.println();
    }

    public void displayOrder() {
        Order order = new Order();

        OrderMenuOption selectedOption;
        do {
            displayOrderMenu();
            int choice = readInt("➤ Choose an option: ");
            selectedOption = OrderMenuOption.fromCode(choice).orElse(null);
            handleOrderMenu(selectedOption, order);
        } while (selectedOption != OrderMenuOption.CANCEL_ORDER);

        System.out.println();
        System.out.println("  Cancelling order...");
        System.out.println();
    }

    private void displayHeader() throws InterruptedException {
        String G2 = "\u001B[38;5;220m";
        String G3 = "\u001B[38;5;214m";
        String G4 = "\u001B[38;5;208m";
        String WH = "\u001B[38;5;255m";
        String DM = "\u001B[38;5;136m";
        String R  = "\u001B[0m";
        String BOLD = "\u001B[1m";

        String TOP = G2 + "  ╔═════════════════════════════════════════════════════════════╗" + R;

        String P = "     ";

        System.out.println();
        System.out.println(TOP);
        System.out.println();

        // LOGO rows 1-4 print instantly
        System.out.println(P + G2 + BOLD + "██████╗ ██╗███████╗███████╗ █████╗ " + G3 + "      PIZZA-LICIOUS");
        System.out.println(P + G2 + BOLD + "██╔══██╗██║╚══███╔╝╚══███╔╝██╔══██╗" + G3 + "      ────────────────");
        System.out.println(P + G3 + BOLD + "██████╔╝██║  ███╔╝   ███╔╝ ███████║" + DM + "      ✦ Fresh & Hot");
        System.out.println(P + G3 + BOLD + "██╔═══╝ ██║ ███╔╝   ███╔╝  ██╔══██║" + DM + "      ✦ Made Your Way");

        // row 5 — sizzle animates on right side inline with banner row
        String[] sizzleFrames = {
                "     🍕· · · · · · · ·     ",
                "     · 🍕· · · · · · ·     ",
                "     · · 🍕· · · · · ·     ",
                "     · · · 🍕· · · · ·     ",
                "     · · · · 🍕· · · ·     ",
                "     · · · · · 🍕· · ·     ",
                "     · · · · · · 🍕· ·     ",
                "     · · · · · · · 🍕·     ",
                "     · · · · · · · · 🍕    ",
                "     · · · · · · · 🍕·     ",
                "     · · · · · · 🍕· ·     ",
                "     · · · · · 🍕· · ·     ",
                "     · · · · 🍕· · · ·     ",
                "     · · · 🍕· · · · ·     ",
                "     · · 🍕· · · · · ·     ",
                "     · 🍕· · · · · · ·     ",
                "     🍕· · · · · · · ·     ",
                "     🔥· · · · · · · ·     ",
                "     ·🔥· · · · · · ·      ",
                "     · ·🔥· · · · · ·      ",
                "     · · ·🔥· · · · ·      ",
                "     · · · ·🔥· · · ·      ",
                "     · · · · ·🔥· · ·      ",
                "     · · · · · ·🔥· ·      ",
                "     · · · · · · ·🔥·      ",
                "     · · · · · · · ·🔥     ",
        };

        for (String frame : sizzleFrames) {
            System.out.print("\r" + P + G4 + BOLD + "██║     ██║███████╗███████╗██║  ██║ " + G3 + frame + R);
            System.out.flush();
            Thread.sleep(80);
        }
        System.out.println();

//        // row 6
//        System.out.println(P + G4 + BOLD + "╚═╝     ╚═╝╚══════╝╚══════╝╚═╝  ╚═╝" + R);
//        System.out.println();

        // LICIOUS animates
        String licious = P + "  ✦  L · I · C · I · O · U · S  ✦";
        System.out.print(BOLD + G2);
        for (char c : licious.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            Thread.sleep(55);
        }
        System.out.println(R);
        System.out.println();
    }

    private void displayHomeMenu() {
        String MID = GOLD + "  ╠═════════════════════════════════════════════════════════════╣" + RESET;
        String BOT = GOLD + "  ╚═════════════════════════════════════════════════════════════╝" + RESET;

        System.out.println(MID);
        System.out.println();

        System.out.println(PADDING + WHITE + "[1]" + GOLD + " " + HomeMenuOption.NEW_ORDER.getLabel() + "          " + WHITE + "[0]" + GOLD + " " + HomeMenuOption.EXIT.getLabel());

        System.out.println();
        System.out.println(BOT);
    }

    private void displayOrderMenu() {
        System.out.println();

        System.out.println("--- ORDER ---");

        System.out.println(OrderMenuOption.ADD_PIZZA.getCode() + ") " + OrderMenuOption.ADD_PIZZA.getLabel());
        System.out.println(OrderMenuOption.ADD_DRINK.getCode() + ") " + OrderMenuOption.ADD_DRINK.getLabel());
        System.out.println(OrderMenuOption.ADD_GARLIC_KNOTS.getCode() + ") " + OrderMenuOption.ADD_GARLIC_KNOTS.getLabel());
        System.out.println(OrderMenuOption.CHECKOUT.getCode() + ") " + OrderMenuOption.CHECKOUT.getLabel());
        System.out.println(OrderMenuOption.CANCEL_ORDER.getCode() + ") " + OrderMenuOption.CANCEL_ORDER.getLabel());
    }

    private void handleOrderMenu(OrderMenuOption option, Order order) {
        if (option == null) {
            System.out.println("  ✗ Invalid option. Please try again.");
            return;
        }

        switch (option) {
            case ADD_PIZZA -> {
                Pizza pizza = processAddPizzaRequest();
                order.addItem(pizza);
            }
            case ADD_DRINK -> {
                Drink drink = processAddDrinkRequest();
                order.addItem(drink);
            }
            case ADD_GARLIC_KNOTS -> {
                GarlicKnots garlicKnots = processAddGarlicKnotsRequest();
                order.addItem(garlicKnots);
            }
            case CHECKOUT -> {
                processCheckoutRequest(order);
            }
            case CANCEL_ORDER -> {
                return;
            }
        }
    }

    private void handleHomeMenu(HomeMenuOption option) {
        if (option == null) {
            System.out.println("  ✗ Invalid option. Please try again.");
            return;
        }

        switch (option) {
            case NEW_ORDER -> displayOrder();
            case EXIT -> { }
        }
    }

    private <T extends Enum<T> & Labelled> T selectFromEnumList(String prompt, T[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i].getLabel());
        }

        int choice = readRangeInt(prompt, 1, options.length);

        return options[choice - 1];
    }

    private <T extends Enum<T> & Labelled> List<T> selectMultipleFromEnumList(String prompt, T[] options) {
        List<T> selected = new ArrayList<>();

        while (true) {
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i].getLabel());
            }

            System.out.println("0) Done");

            int choice = readRangeInt(prompt, 0, options.length);

            if (choice == 0) {
                break;
            }

            T selectedOption = options[choice - 1];

            if (!selected.contains(selectedOption)) {
                selected.add(selectedOption);
            } else {
                System.out.println("Already selected.");
            }
        }

        return selected;
    }

    private List<Topping> selectMultipleFromToppingsList(String prompt, List<Topping> items) {
        List<Topping> selected = new ArrayList<>();

        while (true) {

            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ") " + items.get(i).getName());
            }

            System.out.println("0) Done");

            int choice = readRangeInt(prompt, 0, items.size());

            if (choice == 0) break;

            Topping chosen = items.get(choice - 1);

            if (!selected.contains(chosen)) {
                selected.add(chosen);
            } else {
                System.out.println("Already selected.");
            }
        }

        return selected;
    }

    private Pizza processAddPizzaRequest() {
        List<Topping> meatToppings = ToppingManager.getToppingsByType(ToppingType.MEAT);
        List<Topping> cheeseToppings = ToppingManager.getToppingsByType(ToppingType.CHEESE);
        List<Topping> regularToppings = ToppingManager.getToppingsByType(ToppingType.REGULAR);

        System.out.println();

        CrustType crust = selectFromEnumList("Select your type: ", CrustType.values());
        PizzaSize size = selectFromEnumList("Pizza size: ", PizzaSize.values());

        System.out.println("Toppings");
        List<Topping> meat = selectMultipleFromToppingsList("Select meats:", meatToppings);
        List<Topping> cheese = selectMultipleFromToppingsList("Select cheeses:", cheeseToppings);
        List<Topping> regular = selectMultipleFromToppingsList("Select other toppings:", regularToppings);
        List<SauceType> sauces = selectMultipleFromEnumList("Select sauces: ", SauceType.values());

        boolean isStuffedCrust = readBoolean("Would you like the pizza with stuffed crust?");

        CustomPizza customPizza = new CustomPizza(size, crust, isStuffedCrust);
        meat.forEach(customPizza::addTopping);
        cheese.forEach(customPizza::addTopping);
        regular.forEach(customPizza::addTopping);
        sauces.forEach(customPizza::addSauce);

        return customPizza;
    }

    private Drink processAddDrinkRequest() {
        DrinkSize size = selectFromEnumList("Drink size: ", DrinkSize.values());
        String flavor = readRequiredString("Enter your flavor drink: ");

        return new Drink(flavor, size);
    }

    private GarlicKnots processAddGarlicKnotsRequest() {
        return new GarlicKnots();
    }

    private void processCheckoutRequest(Order order) {
        System.out.println(order);

        System.out.println("1) Confirm");
        System.out.println("2) Cancel");

        int option = readRangeInt("Select an option: ", 1, 2);

        if (option == 1) {
            order.saveReceipt();
        }
    }

}
