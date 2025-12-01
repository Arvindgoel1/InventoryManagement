package InventoryManagementSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory<T extends Item> {
    private HashMap<String, T> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    // Add method with validation
    public void add(T item) throws InvalidQuantityException, DuplicateItemException {
        // Check if quantity is negative
        if (item.getQuantity() < 0) {
            throw new InvalidQuantityException("Quantity cannot be negative for item: " + item.getName());
        }

        // Check for duplicate IDs
        if (items.containsKey(item.getId())) {
            throw new DuplicateItemException("Item with ID " + item.getId() + " already exists in inventory");
        }

        // Add item to HashMap if validations pass
        items.put(item.getId(), item);
    }

    // Remove method
    public void remove(T item) {
        items.remove(item.getId());
    }

    // Get single item by ID
    public T get(String id) {
        return items.get(id);
    }

    // Get all items as a List
    public List<T> getAll() {
        return new ArrayList<>(items.values());
    }
}
