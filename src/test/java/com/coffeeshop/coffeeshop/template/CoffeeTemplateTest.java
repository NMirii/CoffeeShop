package com.coffeeshop.coffeeshop.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Template Method pattern implementations (Espresso, Cappuccino).
 */
class CoffeeTemplateTest {

    /**
     * Verifies that Espresso has the correct description and cost.
     */
    @Test
    void espresso_hasCorrectDescriptionAndCost() {
        CoffeeTemplate espresso = new Espresso();

        assertEquals("Espresso", espresso.getDescription());
        assertEquals(3.0, espresso.getCost());
    }

    /**
     * Verifies that Cappuccino has the correct description and cost.
     */
    @Test
    void cappuccino_hasCorrectDescriptionAndCost() {
        CoffeeTemplate cappuccino = new Cappuccino();

        assertEquals("Cappuccino", cappuccino.getDescription());
        assertEquals(4.0, cappuccino.getCost());
    }

    /**
     * Verifies that preparing Espresso runs without exceptions.
     */
    @Test
    void prepareCoffee_runsWithoutException_forEspresso() {
        CoffeeTemplate espresso = new Espresso();

        espresso.prepareCoffee();
    }

    /**
     * Verifies that preparing Cappuccino runs without exceptions.
     */
    @Test
    void prepareCoffee_runsWithoutException_forCappuccino() {
        CoffeeTemplate cappuccino = new Cappuccino();

        cappuccino.prepareCoffee();
    }
}
