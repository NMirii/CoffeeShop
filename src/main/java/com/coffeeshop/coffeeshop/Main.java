package com.coffeeshop.coffeeshop;

import com.coffeeshop.coffeeshop.facade.CoffeeShopFacade;
import com.coffeeshop.coffeeshop.observer.Customer;
import com.coffeeshop.coffeeshop.strategy.*;
import com.coffeeshop.coffeeshop.adapter.*;
import com.coffeeshop.coffeeshop.singleton.CoffeeShop;

import java.util.Scanner;

/**
 * Main class serves as the entry point for the Coffee Shop application.
 * It provides a command-line interface for customers to place orders.
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Initialize the Facade and Payment Adapter
        CoffeeShopFacade kassa = new CoffeeShopFacade();
        PaymentProcessor stripePayment = new StripeAdapter(new ExternalStripeAPI());

        System.out.println("☕ QƏHVƏ MAĞAZASINA XOŞ GƏLMİSİNİZ! ☕");

        while (true) {
            System.out.println("\n-----------------------------------");
            System.out.print("Adınızı daxil edin (Sistemi bağlamaq üçün 'q' yazın): ");
            String ad = scanner.nextLine();

            if (ad.equalsIgnoreCase("q")) {
                break;
            }

            // Create a new Customer observer
            Customer customer = new Customer(ad);

            System.out.print("Hansı qəhvəni istəyirsiniz? (espresso / cappuccino): ");
            String qehveTipi = scanner.nextLine();

            System.out.print("Süd əlavə edək? (beli / xeyr): ");
            boolean sudIsteyir = scanner.nextLine().equalsIgnoreCase("beli");

            System.out.print("Şəkər əlavə edək? (beli / xeyr): ");
            boolean sekerIsteyir = scanner.nextLine().equalsIgnoreCase("beli");

            System.out.println("Müştəri statusunuz nədir?");
            System.out.println("1. Sadə (Endirim yoxdur)");
            System.out.println("2. Gümüş (10% Endirim)");
            System.out.println("3. Qızıl (20% Endirim)");
            System.out.print("Seçiminiz (1/2/3): ");
            String statusSecimi = scanner.nextLine();

            // Select the appropriate Pricing Strategy
            PricingStrategy strategy;
            if (statusSecimi.equals("2")) {
                strategy = new SilverCustomerStrategy();
            } else if (statusSecimi.equals("3")) {
                strategy = new GoldCustomerStrategy();
            } else {
                strategy = new RegularCustomerStrategy();
            }

            try {
                // Use the Facade to process the order
                kassa.serveCustomer(qehveTipi, sudIsteyir, sekerIsteyir, customer, strategy, stripePayment);
            } catch (Exception e) {
                System.out.println("❌ XƏTA: " + e.getMessage());
            }
        }

        System.out.println("\n=== MAĞAZA BAĞLANDI | GÜNÜN SONU HESABATI ===");
        // Access the shop's total revenue through the Singleton instance
        System.out.println("💰 Ümumi Qazanılan Gəlir: $" + CoffeeShop.getInstance().getTotalRevenue());

        scanner.close();
    }
}