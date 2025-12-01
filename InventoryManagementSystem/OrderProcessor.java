package InventoryManagementSystem;

import java.util.PriorityQueue;

public class OrderProcessor {
    private PriorityQueue<Order> orders;

    public OrderProcessor() {
        this.orders = new PriorityQueue<>();
    }

    // Add order to the queue
    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Added: " + order);
    }

    // Process (remove and return) the highest priority order
    public Order processOrder() {
        return orders.poll();
    }

    // Get the size of remaining orders
    public int getSize() {
        return orders.size();
    }

    // Peek at the next order without removing it
    public Order peekNextOrder() {
        return orders.peek();
    }
}
