package com.coffeeshop.coffeeshop.adapter;

/**
 * ExternalStripeAPI represents a 3rd party payment service.
 * It has its own method naming and logic which might not be compatible with our system's interface.
 */
public class ExternalStripeAPI {
    /**
     * Executes the payment process via the external Stripe API.
     * @param amountDeducted The amount to be deducted from the bank account.
     */
    public void makePayment(double amountDeducted) {
        System.out.println("Stripe API vasitəsilə bankla əlaqə quruldu.");
        System.out.println("Ödəniş uğurla çəkildi: $" + amountDeducted);
    }
}