package com.coffeeshop.coffeeshop.factory;

import com.coffeeshop.coffeeshop.template.Cappuccino;
import com.coffeeshop.coffeeshop.template.CoffeeTemplate;
import com.coffeeshop.coffeeshop.template.Espresso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for CoffeeFactory (Factory Method pattern).
 */
class CoffeeFactoryTest {

    /**
     * Verifies that createCoffee returns an Espresso instance when "espresso" is requested.
     */
    @Test
    void createCoffee_espresso_returnsEspressoInstance() {
        CoffeeTemplate coffee = CoffeeFactory.createCoffee("espresso");

        assertInstanceOf(Espresso.class, coffee);
        assertEquals("Espresso", coffee.getDescription());
        assertEquals(3.0, coffee.getCost());
    }

    /**
     * Verifies that createCoffee returns a Cappuccino instance when "cappuccino" is requested.
     */
    @Test
    void createCoffee_cappuccino_returnsCappuccinoInstance() {
        CoffeeTemplate coffee = CoffeeFactory.createCoffee("cappuccino");

        assertInstanceOf(Cappuccino.class, coffee);
        assertEquals("Cappuccino", coffee.getDescription());
        assertEquals(4.0, coffee.getCost());
    }

    /**
     * Verifies that createCoffee is case-insensitive.
     */
    @Test
    void createCoffee_isCaseInsensitive() {
        CoffeeTemplate coffee = CoffeeFactory.createCoffee("ESPRESSO");
        assertInstanceOf(Espresso.class, coffee);
    }

    /**
     * Verifies that createCoffee throws an IllegalArgumentException for unknown coffee types.
     */
    @Test
    void createCoffee_unknownType_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> CoffeeFactory.createCoffee("latte"));
        assertEquals("Belə bir qəhvə yoxdur: latte", ex.getMessage());
    }

    /**
     * Verifies that createCoffee throws an IllegalArgumentException when coffee type is null.
     */
    @Test
    void createCoffee_nullType_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> CoffeeFactory.createCoffee(null));
        assertEquals("Qəhvə növü boş ola bilməz!", ex.getMessage());
    }
}
