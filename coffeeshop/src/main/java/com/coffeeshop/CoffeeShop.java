package com.coffeeshop;

/**
 * Manages orders and synchronizes access to the OrderQueue.
 * Acts as the central coordinator between customers and baristas.
 */
public class CoffeeShop {

    private final String name;
    private final OrderQueue orderQueue;

    public CoffeeShop(String name, int queueCapacity) {
        this.name = name;
        this.orderQueue = new OrderQueue(queueCapacity);
    }

    public void placeOrder(Order order) {
        try {
            orderQueue.addOrder(order);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("[CoffeeShop] Interrupted while placing order: " + order);
        }
    }

    public Order takeOrder() {
        try {
            return orderQueue.takeOrder();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public void close() {
        System.out.println("[CoffeeShop] " + name + " is closing. No more orders accepted.");
        orderQueue.close();
    }

    public String getName() {
        return name;
    }

    public OrderQueue getOrderQueue() {
        return orderQueue;
    }
}
