package com.coffeeshop.coffeeshop.singleton;

/**
 * CoffeeShop class implements the Singleton pattern.
 * It ensures only one instance of the shop exists to track overall business metrics like revenue.
 */
public class CoffeeShop {
    private static CoffeeShop instance;

    private double totalRevenue = 0;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private CoffeeShop() {
        System.out.println("Qəhvə Mağazası obyekti yaradıldı! (Singleton)");
    }

    /**
     * Static method to get the single instance of CoffeeShop.
     * @return The single instance of the shop.
     */
    public static CoffeeShop getInstance() {
        if (instance == null) {
            instance = new CoffeeShop();
        }
        return instance;
    }

    /**
     * Adds the given amount to the total revenue.
     * @param amount The revenue to add.
     */
    public void addRevenue(double amount) {
        totalRevenue += amount;
    }

    /**
     * Returns the total accumulated revenue.
     * @return Total revenue.
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }
}