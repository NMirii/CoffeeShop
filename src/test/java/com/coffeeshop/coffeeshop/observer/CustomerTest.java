package com.coffeeshop.coffeeshop.observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for Customer, a concrete Observer implementation.
 */
class CustomerTest {

    /**
     * Verifies that getName returns the name passed to the constructor.
     */
    @Test
    void getName_returnsNamePassedToConstructor() {
        Customer customer = new Customer("Nigar");

        assertEquals("Nigar", customer.getName());
    }

    /**
     * Verifies that the update method does not throw an exception when notified.
     */
    @Test
    void update_doesNotThrow_whenNotifiedWithNewStatus() {
        Customer customer = new Customer("Elvin");

        assertDoesNotThrow(() -> customer.update("Hazırdır! Nuş olsun!"));
    }
}
