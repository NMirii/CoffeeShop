package com.coffeeshop;

/**
 * Simulates a customer placing orders in a separate thread.
 */
public class Customer implements Runnable {

    private static final String[] MENU = {
            "Espresso", "Latte", "Cappuccino", "Americano", "Mocha", "Flat White"
    };

    private final String name;
    private final int orderCount;
    private final CoffeeShop coffeeShop;

    public Customer(String name, int orderCount, CoffeeShop coffeeShop) {
        this.name = name;
        this.orderCount = orderCount;
        this.coffeeShop = coffeeShop;
    }

    @Override
    public void run() {
        for (int i = 0; i < orderCount; i++) {
            String item = MENU[(int) (Math.random() * MENU.length)];
            Order order = new Order(name, item);
            System.out.println("[Customer] " + name + " is placing: " + order);
            coffeeShop.placeOrder(order);
            try {
                Thread.sleep((long) (Math.random() * 500) + 100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("[Customer] " + name + " has finished placing orders.");
    }

    public String getName() {
        return name;
    }
}
