package com.coffeeshop.coffeeshop.strategy;

/**
 * RegularCustomerStrategy provides no discount for regular customers.
 */
public class RegularCustomerStrategy implements PricingStrategy {
    /**
     * Returns the base price without any changes.
     * @param rawPrice The base price.
     * @return The same price.
     */
    @Override
    public double calculatePrice(double rawPrice) {
        return rawPrice;
    }
}