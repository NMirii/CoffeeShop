package com.coffeeshop.coffeeshop.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the Adapter pattern (StripeAdapter wrapping ExternalStripeAPI).
 */
@ExtendWith(MockitoExtension.class)
class StripeAdapterTest {

    @Mock
    private ExternalStripeAPI externalStripeAPI;

    /**
     * Verifies that the pay method delegates to externalStripeAPI's makePayment.
     */
    @Test
    void pay_delegatesToExternalStripeAPI_makePayment() {
        PaymentProcessor adapter = new StripeAdapter(externalStripeAPI);

        adapter.pay(15.5);

        verify(externalStripeAPI).makePayment(15.5);
    }

    /**
     * Verifies that the pay method still delegates correctly when amount is zero.
     */
    @Test
    void pay_withZeroAmount_stillDelegatesCorrectly() {
        PaymentProcessor adapter = new StripeAdapter(externalStripeAPI);

        adapter.pay(0.0);

        verify(externalStripeAPI).makePayment(0.0);
    }
}
