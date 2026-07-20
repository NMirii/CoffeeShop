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
    public void brew() { System.out.println("Espresso dəmlənir..."); }
    @Override
    public void addCondiments() { System.out.println("Buxarlanmış süd və qalın köpük əlavə edilir."); }
}