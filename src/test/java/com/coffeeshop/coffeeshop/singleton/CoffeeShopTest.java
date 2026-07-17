package com.coffeeshop.coffeeshop.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Unit tests for the Singleton pattern (CoffeeShop).
 * Note: since CoffeeShop keeps static state, tests check the revenue with relative
 * deltas rather than absolute values, so test order/execution across the JVM doesn't matter.
 */
class CoffeeShopTest {

    /**
     * Verifies that getInstance always returns the same instance (Singleton property).
     */
    @Test
    void getInstance_alwaysReturnsSameInstance() {
        CoffeeShop first = CoffeeShop.getInstance();
        CoffeeShop second = CoffeeShop.getInstance();

        assertSame(first, second);
    }

    /**
     * Verifies that addRevenue correctly accumulates on the shared singleton instance.
     */
    @Test
    void addRevenue_accumulatesOnTheSharedInstance() {
        CoffeeShop shop = CoffeeShop.getInstance();
        double before = shop.getTotalRevenue();

        shop.addRevenue(5.0);
        shop.addRevenue(2.5);

        assertEquals(before + 7.5, shop.getTotalRevenue(), 0.0001);
    }
}
