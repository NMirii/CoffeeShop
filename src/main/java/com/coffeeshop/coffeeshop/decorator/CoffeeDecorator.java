package com.coffeeshop.coffeeshop.decorator;

import com.coffeeshop.coffeeshop.template.CoffeeTemplate;

/**
 * CoffeeDecorator is the base class for all coffee decorators.
 * It follows the Decorator pattern to add extra features to the coffee object.
 */
public abstract class CoffeeDecorator extends CoffeeTemplate {
    /**
     * The coffee object that is being wrapped/decorated.
     */
    protected CoffeeTemplate wrapperCoffee;

    /**
     * Constructor for CoffeeDecorator.
     * @param coffee The coffee instance to decorate.
     */
    public CoffeeDecorator(CoffeeTemplate coffee) {
        this.wrapperCoffee = coffee;
    }

    @Override
    protected void brew() {}

    @Override
    protected void addCondiments() {}
}