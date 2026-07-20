package com.coffeeshop.coffeeshop.factory;

import com.coffeeshop.coffeeshop.template.CoffeeTemplate;
import com.coffeeshop.coffeeshop.template.Espresso;
import com.coffeeshop.coffeeshop.template.Cappuccino;

/**
 * CoffeeFactory implements the Factory Method pattern.
 * It provides a central place for creating different types of coffee objects.
 */
public class CoffeeFactory {
    /**
     * Creates and returns a coffee object based on the given type.
     * @param type The name of the coffee (e.g., "espresso", "cappuccino").
     * @return A concrete instance of CoffeeTemplate.
     * @throws IllegalArgumentException if the coffee type is unknown.
     */
    public static CoffeeTemplate createCoffee(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Qəhvə növü boş ola bilməz!");
        }
        switch (type.toLowerCase()) {
            case "espresso": return new Espresso();
            case "cappuccino": return new Cappuccino();
            default: throw new IllegalArgumentException("Belə bir qəhvə yoxdur: " + type);
        }
    }
}