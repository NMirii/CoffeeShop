package com.coffeeshop.coffeeshop.facade;

import com.coffeeshop.coffeeshop.singleton.CoffeeShop;
import com.coffeeshop.coffeeshop.template.CoffeeTemplate;
import com.coffeeshop.coffeeshop.factory.CoffeeFactory;
import com.coffeeshop.coffeeshop.decorator.MilkDecorator;
import com.coffeeshop.coffeeshop.decorator.SugarDecorator;
import com.coffeeshop.coffeeshop.strategy.PricingStrategy;
import com.coffeeshop.coffeeshop.adapter.PaymentProcessor;
import com.coffeeshop.coffeeshop.prototype.Order;
import com.coffeeshop.coffeeshop.observer.Customer;
import com.coffeeshop.coffeeshop.command.Command;
import com.coffeeshop.coffeeshop.command.PlaceOrderCommand;

/**
 * CoffeeShopFacade simplifies the interaction between the client and the complex coffee preparation system.
 * It coordinates multiple design patterns like Factory, Decorator, Template Method, Strategy, Adapter, Singleton, Prototype, Observer, and Command.
 */
public class CoffeeShopFacade {

    /**
     * Serves a customer by processing their coffee order from start to finish.
     * @param coffeeType Type of coffee (espresso/cappuccino).
     * @param wantMilk Whether the customer wants milk.
     * @param wantSugar Whether the customer wants sugar.
     * @param customer The customer object (Observer).
     * @param strategy The pricing strategy based on customer status.
     * @param paymentProcessor The payment method (Adapter).
     */
    public void serveCustomer(String coffeeType, boolean wantMilk, boolean wantSugar,
                              Customer customer, PricingStrategy strategy, PaymentProcessor paymentProcessor) {

        System.out.println("\n=== YENİ SİFARİŞ PROSESİ BAŞLAYIR ===");

        // 1. Factory: Create base coffee object
        CoffeeTemplate coffee = CoffeeFactory.createCoffee(coffeeType);

        // 2. Decorator: Add optional condiments
        if (wantMilk) {
            coffee = new MilkDecorator(coffee);
        }
        if (wantSugar) {
            coffee = new SugarDecorator(coffee);
        }

        // 3. Template Method: Execute the coffee preparation steps
        coffee.prepareCoffee();

        // 4. Strategy: Calculate the final price based on customer status
        double finalPrice = strategy.calculatePrice(coffee.getCost());
        System.out.println(coffee.getDescription() + " - Yekun qiymət: $" + finalPrice);

        // 5. Adapter & Singleton: Process payment and update shop's total revenue
        paymentProcessor.pay(finalPrice);
        CoffeeShop.getInstance().addRevenue(finalPrice);

        // 6. Prototype & Observer: Create order and register customer for status updates
        Order order = new Order(coffee);
        order.addObserver(customer);

        // 7. Command: Place the order to the kitchen
        Command placeOrder = new PlaceOrderCommand(order);
        placeOrder.execute();

        // Update status triggers Observer notification
        order.setStatus("Hazırdır! Nuş olsun!");

        // Prototype: Demonstrating cloning functionality
        System.out.println("Sistem: Eyni sifarişdən bir dənə də klonlanır...");
        Order clonedOrder = order.cloneOrder();

        System.out.println("=====================================\n");
    }
}