package com.coffeeshop.coffeeshop.observer;

/**
 * OrderObserver interface defines the update method for observers to be notified of state changes.
 */
public interface OrderObserver {
    /**
     * Updates the observer with the new status.
     * @param status The new status information.
     */
    void update(String status);
}