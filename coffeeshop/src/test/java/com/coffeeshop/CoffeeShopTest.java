package com.coffeeshop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for CoffeeShop coordinating customers and baristas.
 */
class CoffeeShopTest {

    @Test
    void testPlaceAndTakeOrder() {
        CoffeeShop shop = new CoffeeShop("Test Shop", 10);
        Order order = new Order("Alice", "Latte");
        shop.placeOrder(order);

        Order taken = shop.takeOrder();
        assertSame(order, taken);
    }

    @Test
    void testTakeOrderReturnsNullAfterClose() {
        CoffeeShop shop = new CoffeeShop("Test Shop", 10);
        shop.close();
        assertNull(shop.takeOrder());
    }

    @Test
    void testAllOrdersProcessedByBaristas() throws InterruptedException {
        CoffeeShop shop = new CoffeeShop("Test Shop", 10);
        int totalOrders = 10;
        AtomicInteger processed = new AtomicInteger(0);

        // Barista threads that count processed orders
        List<Thread> baristaThreads = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(() -> {
                while (true) {
                    Order o = shop.takeOrder();
                    if (o == null) break;
                    processed.incrementAndGet();
                }
            });
            baristaThreads.add(t);
            t.start();
        }

        // Customer threads placing orders
        List<Thread> customerThreads = new ArrayList<>();
        for (int i = 0; i < totalOrders; i++) {
            final int idx = i;
            Thread t = new Thread(() -> shop.placeOrder(new Order("Customer" + idx, "Espresso")));
            customerThreads.add(t);
            t.start();
        }

        for (Thread t : customerThreads) t.join();
        shop.close();
        for (Thread t : baristaThreads) t.join();

        assertEquals(totalOrders, processed.get());
    }

    @Test
    void testOrderQueueIsAccessibleFromShop() {
        CoffeeShop shop = new CoffeeShop("Test Shop", 5);
        assertNotNull(shop.getOrderQueue());
        assertTrue(shop.getOrderQueue().isOpen());
    }

    @Test
    void testShopNameIsCorrect() {
        CoffeeShop shop = new CoffeeShop("Java Brew", 5);
        assertEquals("Java Brew", shop.getName());
    }
}
