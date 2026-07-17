package com.coffeeshop.coffeeshop.decorator;

import com.coffeeshop.coffeeshop.template.CoffeeTemplate;
import com.coffeeshop.coffeeshop.template.Espresso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Decorator pattern (MilkDecorator, SugarDecorator).
 */
class CoffeeDecoratorTest {

    /**
     * Verifies that MilkDecorator correctly adds description and cost.
     */
    @Test
    void milkDecorator_addsDescriptionAndCost() {
        CoffeeTemplate coffee = new MilkDecorator(new Espresso());

        assertEquals("Espresso, Süd", coffee.getDescription());
        assertEquals(3.5, coffee.getCost(), 0.0001);
    }

    /**
     * Verifies that SugarDecorator correctly adds description and cost.
     */
    @Test
    void sugarDecorator_addsDescriptionAndCost() {
        CoffeeTemplate coffee = new SugarDecorator(new Espresso());

        assertEquals("Espresso, Şəkər", coffee.getDescription());
        assertEquals(3.2, coffee.getCost(), 0.0001);
    }

    /**
     * Verifies that multiple decorators can be stacked together.
     */
    @Test
    void decorators_canBeStacked() {
        CoffeeTemplate coffee = new Espresso();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        assertEquals("Espresso, Süd, Şəkər", coffee.getDescription());
        assertEquals(3.7, coffee.getCost(), 0.0001);
    }

    /**
     * Verifies that decorators can be stacked in any order.
     */
    @Test
    void decorators_canBeStackedInAnyOrder() {
        CoffeeTemplate coffee = new Espresso();
        coffee = new SugarDecorator(coffee);
        coffee = new MilkDecorator(coffee);

        assertEquals("Espresso, Şəkər, Süd", coffee.getDescription());
        assertEquals(3.7, coffee.getCost(), 0.0001);
    }

    /**
     * Verifies that preparing decorated coffee runs without exceptions.
     */
    @Test
    void decoratedCoffee_prepareCoffee_runsWithoutException() {
        CoffeeTemplate coffee = new MilkDecorator(new Espresso());

        coffee.prepareCoffee();
    }
}
