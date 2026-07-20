package com.coffeeshop;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Thread-safe queue for managing coffee orders.
 * Producers (customers) add orders; consumers (baristas) take orders.
 */
public class OrderQueue {

    private final Queue<Order> queue = new LinkedList<>();
    private final int capacity;
    private volatile boolean open = true;

    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addOrder(Order order) throws InterruptedException {
        while (queue.size() >= capacity) {
            wait();
        }
        queue.offer(order);
        System.out.println("  [Queue] " + order + " added. Queue size: " + queue.size());
        notifyAll();
    }

    public synchronized Order takeOrder() throws InterruptedException {
        while (queue.isEmpty() && open) {
            wait();
        }
        if (queue.isEmpty()) {
            return null;
        }
        Order order = queue.poll();
        System.out.println("  [Queue] " + order + " taken. Queue size: " + queue.size());
        notifyAll();
        return order;
    }

    public synchronized void close() {
        open = false;
        notifyAll();
    }

    public synchronized boolean isOpen() {
        return open;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
