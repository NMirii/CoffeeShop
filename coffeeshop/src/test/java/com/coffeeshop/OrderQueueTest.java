package com.coffeeshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for OrderQueue thread-safe behaviour.
 */
class OrderQueueTest {

    private OrderQueue queue;

    @BeforeEach
    void setUp() {
        queue = new OrderQueue(3);
    }

    @Test
    void testAddAndTakeOrder() throws InterruptedException {
        Order order = new Order("Alice", "Latte");
        queue.addOrder(order);
        assertEquals(1, queue.size());

        Order taken = queue.takeOrder();
        assertSame(order, taken);
        assertTrue(queue.isEmpty());
    }

    @Test
    void testQueueRespectsFIFO() throws InterruptedException {
        Order o1 = new Order("Alice", "Espresso");
        Order o2 = new Order("Bob", "Mocha");
        Order o3 = new Order("Carol", "Latte");

        queue.addOrder(o1);
        queue.addOrder(o2);
        queue.addOrder(o3);

        assertSame(o1, queue.takeOrder());
        assertSame(o2, queue.takeOrder());
        assertSame(o3, queue.takeOrder());
    }

    @Test
    void testCloseReturnsNullWhenEmpty() throws InterruptedException {
        queue.close();
        assertNull(queue.takeOrder());
    }

    @Test
    void testCloseWithRemainingOrdersDrainsQueue() throws InterruptedException {
        Order order = new Order("Dave", "Americano");
        queue.addOrder(order);
        queue.close();

        // Remaining order should still be retrievable
        assertSame(order, queue.takeOrder());
        // Queue now empty and closed → null
        assertNull(queue.takeOrder());
    }

    @Test
    void testCapacityBlocksProducer() throws InterruptedException {
        // Fill the queue to capacity
        queue.addOrder(new Order("A", "Espresso"));
        queue.addOrder(new Order("B", "Latte"));
        queue.addOrder(new Order("C", "Mocha"));

        AtomicInteger added = new AtomicInteger(0);

        Thread producer = new Thread(() -> {
            try {
                queue.addOrder(new Order("D", "Cappuccino"));
                added.set(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();

        // Give producer time to block
        Thread.sleep(200);
        assertEquals(0, added.get(), "Producer should be blocked on full queue");

        // Consume one to unblock producer
        queue.takeOrder();
        producer.join(1000);
        assertEquals(1, added.get(), "Producer should have added after slot freed");
    }

    @Test
    void testConcurrentProducersAndConsumers() throws InterruptedException {
        int numOrders = 20;
        OrderQueue concurrentQueue = new OrderQueue(5);
        AtomicInteger produced = new AtomicInteger(0);
        AtomicInteger consumed = new AtomicInteger(0);

        List<Thread> producers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            final int idx = i;
            producers.add(new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    try {
                        concurrentQueue.addOrder(new Order("Customer" + idx, "Item" + j));
                        produced.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }

        List<Thread> consumers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            consumers.add(new Thread(() -> {
                while (true) {
                    try {
                        Order o = concurrentQueue.takeOrder();
                        if (o == null) break;
                        consumed.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }));
        }

        consumers.forEach(Thread::start);
        producers.forEach(Thread::start);
        for (Thread t : producers) t.join();

        concurrentQueue.close();
        for (Thread t : consumers) t.join();

        assertEquals(numOrders, produced.get());
        assertEquals(numOrders, consumed.get());
    }
}
