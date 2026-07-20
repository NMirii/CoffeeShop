package com.coffeeshop;

/**
 * Represents a coffee order placed by a customer.
 */
public class Order {

    private static int counter = 0;

    private final int id;
    private final String customerName;
    private final String item;

    public Order(String customerName, String item) {
        this.id = ++counter;
        this.customerName = customerName;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "Order #" + id + " [" + item + "] for " + customerName;
    }
}
