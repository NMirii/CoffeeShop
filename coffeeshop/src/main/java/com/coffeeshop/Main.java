package com.coffeeshop;

import java.util.ArrayList;
import java.util.List;

/**
 * Main application entry point.
 * Sets up the coffee shop simulation with multiple customer and barista threads.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        CoffeeShop shop = new CoffeeShop("Java Brew", 5);

        // Define customers and baristas
        String[] customerNames = {"Alice", "Bob", "Carol", "Dave", "Eve"};
        String[] baristaNames = {"Barista Tom", "Barista Sara"};
        int ordersPerCustomer = 3;

        System.out.println("=== " + shop.getName() + " is now open! ===\n");

        // Start barista threads
        List<Thread> baristaThreads = new ArrayList<>();
        for (String bName : baristaNames) {
            Barista barista = new Barista(bName, shop);
            Thread t = new Thread(barista, bName);
            baristaThreads.add(t);
            t.start();
        }

        // Start customer threads
        List<Thread> customerThreads = new ArrayList<>();
        for (String cName : customerNames) {
            Customer customer = new Customer(cName, ordersPerCustomer, shop);
            Thread t = new Thread(customer, cName);
            customerThreads.add(t);
            t.start();
        }

        // Wait for all customers to finish placing orders
        for (Thread t : customerThreads) {
            t.join();
        }

        // Close the shop so baristas can finish remaining orders and exit
        shop.close();

        // Wait for all baristas to finish
        for (Thread t : baristaThreads) {
            t.join();
        }

        System.out.println("\n=== " + shop.getName() + " has closed for the day. ===");
    }
}
