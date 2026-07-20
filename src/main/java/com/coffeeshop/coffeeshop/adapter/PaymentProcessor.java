package com.coffeeshop.coffeeshop.adapter;

/**
 * PaymentProcessor is the target interface that our application uses for processing payments.
 */
public interface PaymentProcessor {
    /**
     * Method to process payment.
     * @param amount The amount to pay.
     */
    void pay(double amount);
}