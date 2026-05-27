package com.yearupunited.models;

import com.yearupunited.models.interfaces.IMenuItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<IMenuItem> items;
    private final LocalDateTime timestamp;

    public Order() {
        this.items = new ArrayList<>();
        this.timestamp = LocalDateTime.now();
    }

    public void addItem(IMenuItem item) {
        items.add(item);
    }

    public void removeItem(IMenuItem item) {
        items.remove(item);
    }

    public void placeOrder() {
        // check if any pizzas in order
        boolean hasPizza = items.stream().anyMatch(item -> item instanceof Pizza);
        boolean hasDrinkOrKnots = items.stream().anyMatch(item -> item instanceof Drink || item instanceof GarlicKnots);

        if (!hasPizza && !hasDrinkOrKnots) {
            System.out.println("Order must contain at least a drink or garlic knots!");
            return;
        }

        saveReceipt();
    }

    public void cancelOrder() {
        items.clear();
    }

    public void saveReceipt() {
        // creates new yyyyMMdd-HHmmss.txt file for each order inside the receipts folder
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String fileName = timestamp.format(formatter) + ".txt";
        String filePath = "receipts/" + fileName;

        // create receipts folder if it doesn't exist
        File receiptsFolder = new File("receipts");
        if (!receiptsFolder.exists()) {
            receiptsFolder.mkdirs();
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filePath));

            writer.println("===== PIZZA-licious Receipt =====");
            writer.println("Date: " + timestamp);
            writer.println("---------------------------------");

            for (IMenuItem item : items) {
                writer.println(item.getDescription());
                writer.printf("Price: $%.2f%n", item.calculatePrice());
                writer.println("---------------------------------");
            }

            writer.printf("Total: $%.2f%n", getTotal());
            writer.close();

            System.out.println("Receipt saved to " + filePath);

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }

    public double getTotal() {
        double total = 0.0;

        for (IMenuItem item : items) {
            total += item.calculatePrice();
        }

        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== Current Order =====\n");

        // display newest first
        for (int i = items.size() - 1; i >= 0; i--) {
            sb.append(items.get(i).getDescription()).append("\n");
            sb.append(String.format("Price: $%.2f%n", items.get(i).calculatePrice()));
            sb.append("---------------------------------\n");
        }

        sb.append(String.format("Total: $%.2f%n", getTotal()));
        return sb.toString();
    }

}
