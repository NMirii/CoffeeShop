package com.coffeeshop.coffeeshop.strategy;

/**
 * GoldCustomerStrategy provides a 20% discount for gold status customers.
 */
public class GoldCustomerStrategy implements PricingStrategy {
    /**
     * Calculates the discounted price.
     * @param rawPrice The base price of the coffee.
     * @return Discounted price (80% of base).
     */
    @Override
    public double calculatePrice(double rawPrice) {
        return rawPrice * 0.80;
    }
}