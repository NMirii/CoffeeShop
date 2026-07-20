package com.coffeeshop.coffeeshop.decorator;

import com.coffeeshop.coffeeshop.template.CoffeeTemplate;

/**
 * MilkDecorator adds milk to the coffee.
 */
public class MilkDecorator extends CoffeeDecorator {
    /**
     * Constructor for MilkDecorator.
     * @param coffee The coffee to add milk to.
     */
    public MilkDecorator(CoffeeTemplate coffee) {
        super(coffee);
    }

    /**
     * Appends "Milk" to the coffee description.
     * @return Full description of the coffee with milk.
     */
    @Override
    public String getDescription() {
        return wrappedCoffee.getDescription() + ", Süd";
    }

    /**
     * Adds the cost of milk to the coffee price.
     * @return Total cost.
     */
    @Override
    public double getCost() {
        return wrappedCoffee.getCost() + 0.5;
    }

    /**
     * Performs the action of adding milk.
     */
    @Override
    public void addCondiments() {
        wrappedCoffee.addCondiments();
        System.out.println("Əlavə olaraq Süd qatılır...");
    }
}