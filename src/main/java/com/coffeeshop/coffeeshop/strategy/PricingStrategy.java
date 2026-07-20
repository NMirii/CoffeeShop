package com.coffeeshop.coffeeshop.strategy;

/**
 * PricingStrategy interface for the Strategy pattern.
 * Different implementations provide different discount calculation logic.
 */
public interface PricingStrategy {
    /**
     * Calculates the price based on the strategy.
     * @param rawPrice The base price.
     * @return The final price.
     */
    double calculatePrice(double rawPrice);
}