package com.yearupunited.models.services;

import com.yearupunited.models.Topping;
import com.yearupunited.models.enums.ToppingType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToppingManager {

    private static List<Topping> toppings = new ArrayList<>();

    public static void loadToppings(String filePath) {
        toppings = ToppingFileReader.readFromFile(filePath);
    }

    public static List<Topping> getToppingsByType(ToppingType type) {
        return toppings.stream().filter(t -> t.getType() == type).collect(Collectors.toList());
    }
}
