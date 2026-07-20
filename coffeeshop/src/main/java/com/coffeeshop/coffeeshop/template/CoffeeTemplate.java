package com.coffeeshop.coffeeshop.template;

/**
 * CoffeeTemplate defines the Template Method for preparing coffee.
 * It specifies the steps of the algorithm while allowing subclasses to implement specific steps.
 */
public abstract class CoffeeTemplate {
    protected String description;
    protected double price;

    public String getDescription() { return description; }
    public double getCost() { return price; }

    /**
     * The Template Method that defines the sequence of steps to prepare coffee.
     */
    public final void prepareCoffee() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    private void boilWater() { System.out.println("Su qaynayır..."); }
    private void pourInCup() { System.out.println("Qəhvə stəkana süzülür..."); }

    /**
     * Abstract method for brewing the coffee, to be implemented by concrete types.
     */
    public abstract void brew();

    /**
     * Abstract method for adding condiments, can be overridden by decorators or concrete types.
     */
    public abstract void addCondiments();
}