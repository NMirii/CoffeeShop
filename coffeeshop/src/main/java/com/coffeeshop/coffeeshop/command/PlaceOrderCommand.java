package com.coffeeshop.coffeeshop.command;

import com.coffeeshop.coffeeshop.prototype.Order;

/**
 * PlaceOrderCommand is a concrete implementation of the Command pattern.
 * it encapsulates the request to place an order.
 */
public class PlaceOrderCommand implements Command {
    private Order order;

    /**
     * Constructor for PlaceOrderCommand.
     * @param order The order to be processed.
     */
    public PlaceOrderCommand(Order order) {
        this.order = order;
    }

    /**
     * Executes the order placement logic by setting status and informing the kitchen.
     */
    @Override
    public void execute() {
        System.out.println("Sistem: Yeni sifariş qeydə alındı və mətbəxə göndərildi.");
        order.setStatus("Hazırlanır...");
    }
}