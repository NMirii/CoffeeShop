package com.coffeeshop.coffeeshop.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Strategy pattern (Regular, Silver, Gold pricing strategies).
 */
class PricingStrategyTest {

    private static final double RAW_PRICE = 10.0;

    /**
     * Verifies that RegularCustomerStrategy applies no discount.
     */
    @Test
    void regularCustomerStrategy_appliesNoDiscount() {
        PricingStrategy strategy = new RegularCustomerStrategy();

        assertEquals(10.0, strategy.calculatePrice(RAW_PRICE), 0.0001);
    }

    /**
     * Verifies that SilverCustomerStrategy applies a 10% discount.
     */
    @Test
    void silverCustomerStrategy_applies10PercentDiscount() {
        PricingStrategy strategy = new SilverCustomerStrategy();

        assertEquals(9.0, strategy.calculatePrice(RAW_PRICE), 0.0001);
    }

    /**
     * Verifies that GoldCustomerStrategy applies a 20% discount.
     */
    @Test
    void goldCustomerStrategy_applies20PercentDiscount() {
        PricingStrategy strategy = new GoldCustomerStrategy();

        assertEquals(8.0, strategy.calculatePrice(RAW_PRICE), 0.0001);
    }

    /**
     * Verifies that all strategies handle a raw price of zero correctly.
     */
    @Test
    void allStrategies_handleZeroPrice() {
        assertEquals(0.0, new RegularCustomerStrategy().calculatePrice(0), 0.0001);
        assertEquals(0.0, new SilverCustomerStrategy().calculatePrice(0), 0.0001);
        assertEquals(0.0, new GoldCustomerStrategy().calculatePrice(0), 0.0001);
    }
}
