package com.coffeeshop;

/**
 * Simulates a barista preparing orders in a separate thread.
 */
public class Barista implements Runnable {

    private final String name;
    private final CoffeeShop coffeeShop;
    private int ordersServed = 0;

    public Barista(String name, CoffeeShop coffeeShop) {
        this.name = name;
        this.coffeeShop = coffeeShop;
    }

    @Override
    public void run() {
        while (true) {
            Order order = coffeeShop.takeOrder();
            if (order == null) {
                break;
            }
            prepareOrder(order);
        }
        System.out.println("[Barista] " + name + " is done. Orders served: " + ordersServed);
    }

    private void prepareOrder(Order order) {
        System.out.println("[Barista] " + name + " is preparing: " + order);
        try {
            Thread.sleep((long) (Math.random() * 800) + 200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        ordersServed++;
        System.out.println("[Barista] " + name + " completed: " + order);
    }

    public String getName() {
        return name;
    }

    public int getOrdersServed() {
        return ordersServed;
    }
}
