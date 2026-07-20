package com.coffeeshop.coffeeshop.template;

/**
 * Espresso is a concrete implementation of CoffeeTemplate.
 */
public class Espresso extends CoffeeTemplate {
    public Espresso() {
        description = "Espresso";
        price = 3.0;
    }
    @Override
    protected void brew() { System.out.println("Espresso dəmlənir..."); }
    @Override
    protected void addCondiments() { System.out.println("Heç bir əlavə qatılmır (Sadə)."); }
}