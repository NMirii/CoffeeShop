package com.coffeeshop.coffeeshop.decorator;

import com.coffeeshop.coffeeshop.template.CoffeeTemplate;

/**
 * SugarDecorator adds sugar to the coffee.
 */
public class SugarDecorator extends CoffeeDecorator {
    /**
     * Constructor for SugarDecorator.
     * @param coffee The coffee to add sugar to.
     */
    public SugarDecorator(CoffeeTemplate coffee) {
        super(coffee);
    }

    /**
     * Appends "Sugar" to the coffee description.
     * @return Full description of the coffee with sugar.
     */
    @Override
    public String getDescription() {
        return wrapperCoffee.getDescription() + ", Şəkər";
    }

    /**
     * Adds the cost of sugar to the coffee price.
     * @return Total cost.
     */
    @Override
    public double getCost() {
        return wrapperCoffee.getCost() + 0.2;
    }

    /**
     * Performs the action of adding sugar.
     */
    @Override
    protected void addCondiments() {
        System.out.println("Əlavə olaraq Şəkər qatılır...");
    }
}