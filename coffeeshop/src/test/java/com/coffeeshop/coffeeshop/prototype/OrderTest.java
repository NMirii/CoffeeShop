package com.coffeeshop.coffeeshop.prototype;

import com.coffeeshop.coffeeshop.observer.OrderObserver;
import com.coffeeshop.coffeeshop.template.Espresso;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Unit tests for Order, which implements both the Prototype pattern (cloneOrder)
 * and acts as the Subject in the Observer pattern (addObserver/setStatus).
 */
class OrderTest {

    /**
     * Verifies that cloneOrder returns a new instance with the same coffee object.
     */
    @Test
    void cloneOrder_returnsNewInstance_withSameCoffee() {
        Order order = new Order(new Espresso());

        Order cloned = order.cloneOrder();

        assertNotSame(order, cloned);
        assertSame(order.getCoffee(), cloned.getCoffee());
    }

    /**
     * Verifies that cloneOrder does not copy registered observers to the new instance.
     */
    @Test
    void cloneOrder_doesNotCopyRegisteredObservers() {
        Order order = new Order(new Espresso());
        List<String> notifications = new ArrayList<>();
        order.addObserver(status -> notifications.add(status));

        Order cloned = order.cloneOrder();
        cloned.setStatus("Hazırdır!");

        assertEquals(0, notifications.size());
    }

    /**
     * Verifies that setStatus notifies all registered observers.
     */
    @Test
    void setStatus_notifiesAllRegisteredObservers() {
        Order order = new Order(new Espresso());
        List<String> observer1Updates = new ArrayList<>();
        List<String> observer2Updates = new ArrayList<>();

        order.addObserver(status -> observer1Updates.add(status));
        order.addObserver(status -> observer2Updates.add(status));

        order.setStatus("Hazırlanır...");

        assertEquals(List.of("Hazırlanır..."), observer1Updates);
        assertEquals(List.of("Hazırlanır..."), observer2Updates);
    }

    /**
     * Verifies that setStatus notifies observers every time it is updated.
     */
    @Test
    void setStatus_multipleUpdates_notifiesObserverEachTime() {
        Order order = new Order(new Espresso());
        List<String> updates = new ArrayList<>();
        OrderObserver observer = updates::add;
        order.addObserver(observer);

        order.setStatus("Qəbul edildi");
        order.setStatus("Hazırlanır...");
        order.setStatus("Hazırdır!");

        assertEquals(List.of("Qəbul edildi", "Hazırlanır...", "Hazırdır!"), updates);
    }
}
