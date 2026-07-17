package com.coffeeshop.coffeeshop.template;

/**
 * Cappuccino is a concrete implementation of CoffeeTemplate.
 */
public class Cappuccino extends CoffeeTemplate {
    public Cappuccino() {
        description = "Cappuccino";
        price = 4.0;
    }
    @Override
    protected void brew() { System.out.println("Espresso dəmlənir..."); }
    @Override
    protected void addCondiments() { System.out.println("Buxarlanmış süd və qalın köpük əlavə edilir."); }
}