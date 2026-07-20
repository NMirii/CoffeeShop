package com.coffeeshop.coffeeshop.facade;

import com.coffeeshop.coffeeshop.adapter.PaymentProcessor;
import com.coffeeshop.coffeeshop.observer.Customer;
import com.coffeeshop.coffeeshop.singleton.CoffeeShop;
import com.coffeeshop.coffeeshop.strategy.GoldCustomerStrategy;
import com.coffeeshop.coffeeshop.strategy.RegularCustomerStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

/**
 * Integration-style tests for CoffeeShopFacade, which coordinates Factory, Decorator,
 * Template Method, Strategy, Adapter, Singleton, Prototype, Observer and Command together.
 */
@ExtendWith(MockitoExtension.class)
class CoffeeShopFacadeTest {

    @Mock
    private PaymentProcessor paymentProcessor;

    /**
     * Verifies that serveCustomer charges the correct price for plain Espresso with no discount.
     */
    @Test
    void serveCustomer_chargesCorrectPrice_forPlainEspressoWithNoDiscount() {
        CoffeeShopFacade facade = new CoffeeShopFacade();
        Customer customer = new Customer("Aysel");

        facade.serveCustomer("espresso", false, false, customer,
                new RegularCustomerStrategy(), paymentProcessor);

        verify(paymentProcessor).pay(3.0);
    }

    /**
     * Verifies that serveCustomer charges the correct price for Cappuccino with Milk, Sugar, and Gold discount.
     */
    @Test
    void serveCustomer_chargesCorrectPrice_forCappuccinoWithMilkSugarAndGoldDiscount() {
        CoffeeShopFacade facade = new CoffeeShopFacade();
        Customer customer = new Customer("Kamran");

        facade.serveCustomer("cappuccino", true, true, customer,
                new GoldCustomerStrategy(), paymentProcessor);

        verify(paymentProcessor).pay(4.7 * 0.80);
    }

    /**
     * Verifies that serveCustomer adds the final price to the total shop revenue.
     */
    @Test
    void serveCustomer_addsFinalPriceToShopRevenue() {
        CoffeeShopFacade facade = new CoffeeShopFacade();
        Customer customer = new Customer("Farid");
        double revenueBefore = CoffeeShop.getInstance().getTotalRevenue();

        facade.serveCustomer("espresso", false, false, customer,
                new RegularCustomerStrategy(), paymentProcessor);

        assertEquals(revenueBefore + 3.0, CoffeeShop.getInstance().getTotalRevenue(), 0.0001);
    }

    /**
     * Verifies that serveCustomer throws an IllegalArgumentException for unknown coffee types.
     */
    @Test
    void serveCustomer_withUnknownCoffeeType_throwsIllegalArgumentException() {
        CoffeeShopFacade facade = new CoffeeShopFacade();
        Customer customer = new Customer("Leyla");

        assertThrows(IllegalArgumentException.class, () ->
                facade.serveCustomer("latte", false, false, customer,
                        new RegularCustomerStrategy(), paymentProcessor));
    }
}
