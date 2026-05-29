package com.yearupunited.ui;

import com.yearupunited.models.*;
import com.yearupunited.models.enums.*;
import com.yearupunited.models.interfaces.ILabelled;
import com.yearupunited.models.services.SoundPlayer;
import com.yearupunited.models.services.ToppingManager;
import com.yearupunited.ui.enums.HomeMenuOption;
import com.yearupunited.ui.enums.OrderMenuOption;
import com.yearupunited.ui.enums.PizzaMenuOption;

import java.util.ArrayList;
import java.util.List;

import static com.yearupunited.ui.Helper.*;
import static com.yearupunited.ui.ConsoleStyle.*;

public class UserInterface {

    private boolean firstHomeVisit = true;

    public void displayHome() throws InterruptedException {
        // Loads available toppings from resources/toppings.txt
        init();

        displayHeader();

        HomeMenuOption selectedOption;
        do {
            displayHomeMenu();

            int choice = readRangeInt(GOLD + "  >> " + WHITE, 0, 1);

            selectedOption = HomeMenuOption.fromCode(choice).orElse(null);
            handleHomeMenu(selectedOption);

            firstHomeVisit = false;
        } while (selectedOption != HomeMenuOption.EXIT);

        System.out.println();
        System.out.println(AMBER + "  Thank you for visiting PIZZA-licious. Goodbye!" + RESET);
    }

    private void init() {
        ToppingManager.loadToppings("src/main/resources/toppings.txt");
    }

    public void displayOrder() {
        Order order = new Order();

        OrderMenuOption selectedOption;

        boolean exit;
        do {
            displayOrderMenu();

            int choice = readRangeInt(GOLD + "  >> " + WHITE, 0, 4);
            selectedOption = OrderMenuOption.fromCode(choice).orElse(null);
            exit = handleOrderMenu(selectedOption, order);
        } while (!exit);

        System.out.println(WHITE + "  Returning to home screen..." + RESET);
        System.out.println();
    }

    private void displayHeader() throws InterruptedException {
        String TOP = BRIGHT_GOLD + "  ╔═════════════════════════════════════════════════════════════╗" + RESET;

        System.out.println();
        System.out.println(TOP);
        System.out.println();

        System.out.println(PADDING + BRIGHT_GOLD + BOLD + "██████╗ ██╗███████╗███████╗ █████╗ " + AMBER + "      PIZZA-LICIOUS");
        System.out.println(PADDING + BRIGHT_GOLD + BOLD + "██╔══██╗██║╚══███╔╝╚══███╔╝██╔══██╗" + AMBER + "      ────────────────");
        System.out.println(PADDING + AMBER + BOLD + "██████╔╝██║  ███╔╝   ███╔╝ ███████║" + BURNT_AMBER + "      ✦ Fresh & Hot");
        System.out.println(PADDING + AMBER + BOLD + "██╔═══╝ ██║ ███╔╝   ███╔╝  ██╔══██║" + BURNT_AMBER + "      ✦ Made Your Way");

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
            System.out.print("\r" + PADDING + DEEP_ORANGE + BOLD + "██║     ██║███████╗███████╗██║  ██║ " + AMBER + frame + RESET);
            System.out.flush();
            Thread.sleep(80);
        }
        System.out.println();

        String licious = PADDING + "  ✦  L · I · C · I · O · U · S  ✦";
        System.out.print(BOLD + BRIGHT_GOLD);
        for (char c : licious.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            Thread.sleep(55);
        }
        System.out.println(RESET);
        System.out.println();

        SoundPlayer.play("/Users/cjdeniz/projects/PIZZA-licious/sounds/pizza-pizza.wav");
    }

    // ── SHARED LAYOUT OUTLINES ──────────────────────────────────────

    private void printScreenHeader(String title) {
        String TOP = GOLD + "  ╔═════════════════════════════════════════════════════════════╗" + RESET;
        int width = 61;
        int leftPadding = (width - title.length()) / 2;
        String centeredTitle = WHITE + BOLD + " ".repeat(Math.max(0, leftPadding)) + title;
        System.out.println(centeredTitle);
        System.out.println(TOP);
        System.out.println();
    }

    private void printScreenFooter() {
        System.out.println();
        System.out.println(GOLD + "  ╚═════════════════════════════════════════════════════════════╝" + RESET);
    }

    private void printMid() {
        System.out.println(GOLD + "  ╠═════════════════════════════════════════════════════════════╣" + RESET);
    }

    // ── MENUS ──────────────────────────────────────────────────────

    private void displayHomeMenu() {
        if (firstHomeVisit) {
            printMid();
        } else {
            System.out.println(GOLD + "  ╔═════════════════════════════════════════════════════════════╗" + RESET);
        }

        System.out.println();
        System.out.println(PADDING + WHITE + "[1]" + GOLD + " " + HomeMenuOption.NEW_ORDER.getLabel() +
                "          " + WHITE + "[0]" + GOLD + " " + HomeMenuOption.EXIT.getLabel());
        System.out.println();
        System.out.println(GOLD + "  ╚═════════════════════════════════════════════════════════════╝" + RESET);
    }

    private void displayOrderMenu() {
        System.out.println();

        printScreenHeader("ORDER");

        System.out.printf(PADDING + WHITE + "[1]" + GOLD + " %-25s " + WHITE + "[2]" + GOLD + " %s%n",
                OrderMenuOption.ADD_PIZZA.getLabel(), OrderMenuOption.ADD_DRINK.getLabel());
        System.out.println();
        System.out.printf(PADDING + WHITE + "[3]" + GOLD + " %-25s " + WHITE + "[4]" + GOLD + " %s%n",
                OrderMenuOption.ADD_GARLIC_KNOTS.getLabel(), OrderMenuOption.CHECKOUT.getLabel());
        System.out.println();
        System.out.printf(PADDING + WHITE + "[0]" + GOLD + " %s%n", OrderMenuOption.CANCEL_ORDER.getLabel());

        printScreenFooter();
    }

    private void displayPizzaMenu() {
        System.out.println();

        printScreenHeader("ADD PIZZA");

        System.out.printf(PADDING + WHITE + "[1]" + GOLD + " %-25s " + WHITE + "[2]" + GOLD + " %s%n",
                PizzaMenuOption.SIGNATURE.getLabel(), PizzaMenuOption.CUSTOM.getLabel());

        printScreenFooter();
    }

    // ── HANDLERS ───────────────────────────────────────────────────

    private void handleHomeMenu(HomeMenuOption option) {
        if (option == null) {
            System.out.println(WHITE + "  ✗ Invalid option. Please try again." + RESET);
            return;
        }

        switch (option) {
            case NEW_ORDER -> displayOrder();
            case EXIT -> { }
        }
    }

    private boolean handleOrderMenu(OrderMenuOption option, Order order) {
        if (option == null) {
            System.out.println(WHITE + "  ✗ Invalid option. Please try again." + RESET);
            return false;
        }

        switch (option) {
            case ADD_PIZZA -> {
                Pizza pizza = handlePizzaMenu();
                if (pizza != null) order.addItem(pizza);
            }
            case ADD_DRINK -> {
                Drink drink = processAddDrinkRequest();
                order.addItem(drink);
            }
            case ADD_GARLIC_KNOTS -> {
                    order.addItem(new GarlicKnots());
                    System.out.println(WHITE + "  ✓ Garlic knots added to order!" + RESET);
            }
            case CHECKOUT -> {
                processCheckoutRequest(order);
                return true;
            }
            case CANCEL_ORDER -> {
                order.cancelOrder();
                return true;
            }
        }
        return false;
    }

    private Pizza handlePizzaMenu() {
        displayPizzaMenu();

        int choice = readInt(GOLD + "  >> " + WHITE);
        PizzaMenuOption selectedOption = PizzaMenuOption.fromCode(choice).orElse(null);

        if (selectedOption == null) {
            System.out.println(WHITE + "  ✗ Invalid option." + RESET);
            return null;
        }

        switch (selectedOption) {
            case SIGNATURE -> { return processAddSignaturePizzaRequest(); }
            case CUSTOM -> { return processAddPizzaRequest(); }
            default -> { return null; }
        }
    }

    private void handleAddToppings(Pizza pizza) {
        List<Topping> meatToppings = ToppingManager.getToppingsByType(ToppingType.MEAT);
        List<Topping> cheeseToppings = ToppingManager.getToppingsByType(ToppingType.CHEESE);
        List<Topping> regularToppings = ToppingManager.getToppingsByType(ToppingType.REGULAR);

        List<Topping> meat = selectMultipleFromToppingsList("Select meats:", meatToppings, pizza);
        List<Topping> cheese = selectMultipleFromToppingsList("Select cheeses:", cheeseToppings, pizza);
        List<Topping> regular = selectMultipleFromToppingsList("Select other toppings:", regularToppings, pizza);

        meat.forEach(pizza::addTopping);
        cheese.forEach(pizza::addTopping);
        regular.forEach(pizza::addTopping);
    }

    private void handleRemoveToppings(Pizza pizza) {
        List<Topping> meatToppings = ToppingManager.getToppingsByType(ToppingType.MEAT);
        List<Topping> cheeseToppings = ToppingManager.getToppingsByType(ToppingType.CHEESE);
        List<Topping> regularToppings = ToppingManager.getToppingsByType(ToppingType.REGULAR);

        List<Topping> meat = selectMultipleExistingToppings("Select meats:", meatToppings, pizza);
        List<Topping> cheese = selectMultipleExistingToppings("Select cheeses:", cheeseToppings, pizza);
        List<Topping> regular = selectMultipleExistingToppings("Select other toppings:", regularToppings, pizza);

        meat.forEach(pizza::removeTopping);
        cheese.forEach(pizza::removeTopping);
        regular.forEach(pizza::removeTopping);
    }

    // ── SELECTION HELPERS ──────────────────────────────────────────

    private <T extends Enum<T> & ILabelled> T selectFromEnumList(String prompt, T[] options) {
        System.out.println();
        printScreenHeader(prompt.toUpperCase().replace(":", "").trim());

        for (int i = 0; i < options.length; i++) {
            System.out.println(PADDING + WHITE + "[" + (i + 1) + "]" + GOLD + " " + options[i].getLabel());
        }

        printScreenFooter();

        int choice = readRangeInt(GOLD + "  >> " + WHITE, 1, options.length);
        return options[choice - 1];
    }

    private <T extends Enum<T> & ILabelled> List<T> selectMultipleFromEnumList(String prompt, T[] options) {

        List<T> selected = new ArrayList<>();

        while (true) {

            System.out.println();
            printScreenHeader(prompt.toUpperCase().replace(":", "").trim());

            // display each option with a number starting at 1
            for (int i = 0; i < options.length; i++) {
                System.out.println(PADDING + WHITE + "[" + (i + 1) + "]" + GOLD + " " + options[i].getLabel());
            }

            // always show 0 as the exit option
            System.out.println(PADDING + WHITE + "[0]" + GOLD + " Done");

            printScreenFooter();

            // read a valid number between 0 and the number of options
            int choice = readRangeInt(GOLD + "  >> " + WHITE, 0, options.length);

            // 0 means the user is done selecting
            if (choice == 0) break;

            // convert to 0-based array index
            T selectedOption = options[choice - 1];

            // only add if not already selected to avoid duplicates
            if (!selected.contains(selectedOption)) {
                selected.add(selectedOption);
                System.out.println(WHITE + "  ✓ " + selectedOption.getLabel() + " added." + RESET);
            } else {
                System.out.println(WHITE + "  ✗ Already selected." + RESET);
            }
        }

        // return the final list of selected options
        return selected;
    }

    private List<Topping> selectMultipleFromToppingsList(String prompt, List<Topping> items, Pizza pizza) {
        List<Topping> selected = new ArrayList<>();

        while (true) {
            System.out.println();
            printScreenHeader(prompt.toUpperCase().replace(":", "").trim());

            for (int i = 0; i < items.size(); i++) {
                System.out.println(PADDING + WHITE + "[" + (i + 1) + "]" + GOLD + " " + items.get(i).getName());
            }
            System.out.println(PADDING + WHITE + "[0]" + GOLD + " Done");

            printScreenFooter();

            int choice = readRangeInt(GOLD + "  >> " + WHITE, 0, items.size());
            if (choice == 0) break;

            Topping chosen = items.get(choice - 1);

            if (pizza.getToppings().contains(chosen)) {
                System.out.println(WHITE + "  ✗ Pizza already contains this topping." + RESET);
            } else if (selected.contains(chosen)) {
                System.out.println(WHITE + "  ✗ Already selected." + RESET);
            } else {
                selected.add(chosen);
                System.out.println(WHITE + "  ✓ " + chosen.getName() + " added." + RESET);
            }
        }

        return selected;
    }

    private List<Topping> selectMultipleExistingToppings(String prompt, List<Topping> items, Pizza pizza) {
        List<Topping> selected = new ArrayList<>();

        while (true) {
            System.out.println();
            printScreenHeader(prompt.toUpperCase().replace(":", "").trim());

            for (int i = 0; i < items.size(); i++) {
                System.out.println(PADDING + WHITE + "[" + (i + 1) + "]" + GOLD + " " + items.get(i).getName());
            }
            System.out.println(PADDING + WHITE + "[0]" + GOLD + " Done");

            printScreenFooter();

            int choice = readRangeInt(GOLD + "  >> " + WHITE, 0, items.size());
            if (choice == 0) break;

            Topping chosen = items.get(choice - 1);

            if (!pizza.getToppings().contains(chosen)) {
                System.out.println(GOLD + "  ✗ Pizza does not contain this topping." + RESET);
            } else if (selected.contains(chosen)) {
                System.out.println(GOLD + "  ✗ Already selected." + RESET);
            } else {
                selected.add(chosen);
                System.out.println(GOLD + "  ✓ " + chosen.getName() + " removed." + RESET);
            }
        }

        return selected;
    }

    // ── PROCESSES ──────────────────────────────────────────────────

    private Pizza processAddPizzaRequest() {
        PizzaSize size = selectFromEnumList("PIZZA SIZE", PizzaSize.values());
        CrustType crust = selectFromEnumList("CRUST TYPE", CrustType.values());

        System.out.println();

        boolean isStuffedCrust = readBoolean("  Would you like stuffed crust?");
        List<SauceType> sauces = selectMultipleFromEnumList("SELECT SAUCES", SauceType.values());

        CustomPizza customPizza = new CustomPizza(size, crust, isStuffedCrust);
        sauces.forEach(customPizza::addSauce);

        System.out.println();
        System.out.println(GOLD + "  ── TOPPINGS ──────────────────────────────────────────────────" + RESET);
        handleAddToppings(customPizza);

        System.out.println(WHITE + "  ✓ Pizza added to order!" + RESET);
        return customPizza;
    }

    private Pizza processAddSignaturePizzaRequest() {
        MargheritaPizza margheritaPizza = new MargheritaPizza();
        VeggiePizza veggiePizza = new VeggiePizza();

        System.out.println();
        printScreenHeader("SIGNATURE PIZZAS");

        System.out.printf(PADDING + WHITE + "[1]" + GOLD + " %-25s " + WHITE + "[2]" + GOLD + " %s%n",
                margheritaPizza.getName(), veggiePizza.getName());

        printScreenFooter();

        int choice = readRangeInt(GOLD + "  >> " + WHITE, 1, 2);

        SignaturePizza pizza;
        switch (choice) {
            case 1 -> pizza = new MargheritaPizza();
            case 2 -> pizza = new VeggiePizza();
            default -> { return null; }
        }

        processCustomizeSignaturePizzaRequest(pizza);
        System.out.println(WHITE + "  ✓ Pizza added to order!" + RESET);
        return pizza;
    }

    private void processCustomizeSignaturePizzaRequest(SignaturePizza signaturePizza) {
        System.out.println();
        printScreenHeader("CUSTOMIZE " + signaturePizza.getName().toUpperCase());

        String[] lines = signaturePizza.toString().split("\n");
        for (String line : lines) {
            System.out.println(PADDING + WHITE + line);
        }

        System.out.println();
        System.out.printf(PADDING + WHITE + "[1]" + GOLD + " %-25s " + WHITE + "[2]" + GOLD + " %s%n",
                "Add toppings", "Remove toppings");
        System.out.println();
        System.out.printf(PADDING + WHITE + "[3]" + GOLD + " %s%n", "Leave as is");

        printScreenFooter();

        int option = readRangeInt(GOLD + "  >> " + WHITE, 1, 3);

        if (option == 1) {
            handleAddToppings(signaturePizza);
        } else if (option == 2) {
            handleRemoveToppings(signaturePizza);
        }
    }

    private Drink processAddDrinkRequest() {
        DrinkSize size = selectFromEnumList("DRINK SIZE", DrinkSize.values());
        String flavor = readRequiredString(GOLD + "  Enter flavor: " + WHITE);
        System.out.println(WHITE + "  ✓ Drink added to order!" + RESET);
        return new Drink(flavor, size);
    }

    private void processCheckoutRequest(Order order) {
        System.out.println();
        printScreenHeader("CHECKOUT");

        String[] lines = order.toString().split("\n");
        for (String line : lines) {
            System.out.println(PADDING + WHITE + line);
        }

        System.out.println();
        System.out.printf(PADDING + WHITE + "[1]" + GOLD + " %-25s " + WHITE + "[0]" + GOLD + " %s%n",
                "Confirm", "Cancel");

        printScreenFooter();

        int option = readRangeInt(GOLD + "  >> " + WHITE, 0, 1);

        if (option == 1) {
            order.placeOrder();
        } else if (option == 0) {
            order.cancelOrder();
        }
    }
}
