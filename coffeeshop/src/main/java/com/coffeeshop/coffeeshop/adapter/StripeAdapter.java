package com.coffeeshop.coffeeshop.adapter;

/**
 * StripeAdapter implements the Adapter design pattern.
 * It adapts the ExternalStripeAPI to the PaymentProcessor interface used by our application.
 */
public class StripeAdapter implements PaymentProcessor {

    private ExternalStripeAPI stripeAPI;

    /**
     * Constructor for StripeAdapter.
     * @param stripeAPI The external Stripe API instance to be adapted.
     */
    public StripeAdapter(ExternalStripeAPI stripeAPI) {
        this.stripeAPI = stripeAPI;
    }

    /**
     * Implementation of the pay method from PaymentProcessor interface.
     * It maps the call to the external makePayment method.
     * @param amount The amount to pay.
     */
    @Override
    public void pay(double amount) {
        System.out.println("Adapter: Ödəniş sorğusu Stripe sisteminə yönləndirilir...");
        stripeAPI.makePayment(amount);
    }
}