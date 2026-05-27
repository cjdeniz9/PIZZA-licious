package com.yearupunited.models.services;

import com.yearupunited.models.Topping;
import com.yearupunited.models.enums.ToppingType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToppingFileReader {

    public static List<Topping> readFromFile(String filePath) {
        List<Topping> toppings = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                String name = parts[0].trim();
                ToppingType type = ToppingType.valueOf(parts[1].trim());

                toppings.add(new Topping(name, type));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Toppings file not found: " + e.getMessage());
        }

        return toppings;
    }
}
