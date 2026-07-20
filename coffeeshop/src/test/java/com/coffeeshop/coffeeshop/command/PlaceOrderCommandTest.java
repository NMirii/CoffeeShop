package com.coffeeshop.coffeeshop.command;

import com.coffeeshop.coffeeshop.prototype.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the Command pattern (PlaceOrderCommand).
 */
@ExtendWith(MockitoExtension.class)
class PlaceOrderCommandTest {

    @Mock
    private Order order;

    /**
     * Verifies that executing the command sets the order status to "In Preparation".
     */
    @Test
    void execute_setsOrderStatusToInPreparation() {
        Command command = new PlaceOrderCommand(order);

        command.execute();

        verify(order).setStatus("Hazırlanır...");
    }
}
