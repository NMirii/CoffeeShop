package com.coffeeshop.coffeeshop.prototype;

import com.coffeeshop.coffeeshop.template.CoffeeTemplate;
import com.coffeeshop.coffeeshop.observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Order class represents a coffee order and implements the Prototype pattern for cloning.
 * It also acts as the Subject in the Observer pattern to notify customers about status updates.
 */
public class Order implements OrderPrototype {
    private CoffeeTemplate coffee;
    private String status;

    private List<OrderObserver> observers = new ArrayList<>();

    /**
     * Constructor for Order.
     * @param coffee The coffee template object associated with this order.
     */
    public Order(CoffeeTemplate coffee) {
        this.coffee = coffee;
        this.status = "Qəbul edildi";
    }

    /**
     * Getter for coffee.
     * @return The coffee template instance.
     */
    public CoffeeTemplate getCoffee() {
        return coffee;
    }

    /**
     * Adds an observer (e.g., a customer) to be notified of status changes.
     * @param observer The observer instance.
     */
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    /**
     * Sets the order status and notifies all registered observers.
     * @param status The new status string.
     */
    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    /**
     * Internal method to notify all observers about a status update.
     */
    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this.status);
        }
    }

    /**
     * Implementation of the Prototype's clone method.
     * @return A new instance of Order with the same coffee object.
     */
    @Override
    public Order cloneOrder() {
        return new Order(this.coffee);
    }

    /**
     * Prints the details of the order to the console.
     */
    public void printOrderDetails() {
        System.out.println("Sifariş detalı: " + coffee.getDescription() + " | Cari Status: " + status);
    }
}