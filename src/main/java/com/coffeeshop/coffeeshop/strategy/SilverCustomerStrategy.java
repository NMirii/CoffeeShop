package com.coffeeshop.coffeeshop.strategy;

/**
 * SilverCustomerStrategy provides a 10% discount for silver status customers.
 */
public class SilverCustomerStrategy implements PricingStrategy {
    /**
     * Calculates the discounted price.
     * @param rawPrice The base price of the coffee.
     * @return Discounted price (90% of base).
     */
    @Override
    public double calculatePrice(double rawPrice) {
        return rawPrice * 0.90;
    }
}