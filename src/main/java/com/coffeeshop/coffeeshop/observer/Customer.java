package com.coffeeshop.coffeeshop.observer;

/**
 * Customer is a concrete observer that receives notifications about order status changes.
 */
public class Customer implements OrderObserver {
    private String name;

    /**
     * Constructor for Customer.
     * @param name Name of the customer.
     */
    public Customer(String name) {
        this.name = name;
    }

    /**
     * Getter for customer name.
     * @return The customer name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method called when the observed subject (Order) changes its status.
     * @param status The new status of the order.
     */
    @Override
    public void update(String status) {
        System.out.println("BİLDİRİŞ -> Salam " + name + ", sifarişinin statusu yeniləndi: " + status);
    }
}