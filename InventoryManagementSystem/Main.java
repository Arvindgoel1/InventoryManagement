package InventoryManagementSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create inventory items
        List<Item> inventory = new ArrayList<>();
        
        // Add Books
        inventory.add(new Book("B001", "Java Programming", 599.99, 10, "James Gosling"));
        inventory.add(new Book("B002", "Data Structures", 449.50, 15, "Robert Sedgewick"));
        inventory.add(new Book("B003", "Design Patterns", 750.00, 8, "Gang of Four"));
        
        // Add Clothing
        inventory.add(new Clothing("C001", "T-Shirt", 299.99, 50, "L"));
        inventory.add(new Clothing("C002", "Jeans", 899.00, 30, "M"));
        inventory.add(new Clothing("C003", "Jacket", 1499.99, 20, "XL"));
        
        // Add Electronics
        inventory.add(new Electronics("E001", "Laptop", 45999.00, 5, 24));
        inventory.add(new Electronics("E002", "Smartphone", 25999.00, 12, 12));
        inventory.add(new Electronics("E003", "Headphones", 2999.00, 25, 6));
        
        System.out.println("=== INVENTORY MANAGEMENT SYSTEM ===\n");
        
        // Display all items
        System.out.println("--- All Items ---");
        displayInventory(inventory);
        
        // Sort items by price (ascending)
        Collections.sort(inventory);
        System.out.println("\n--- Items Sorted by Price (Ascending) ---");
        displayInventory(inventory);
        
        // Demonstrate item operations
        System.out.println("\n--- Item Operations ---");
        Item laptop = inventory.get(inventory.size() - 1); // Most expensive item
        System.out.println("Original price of " + laptop.getName() + ": Rs." + laptop.getPrice());
        laptop.setPrice(42999.00);
        System.out.println("Updated price: Rs." + laptop.getPrice());
        
        System.out.println("\nOriginal quantity: " + laptop.getQuantity());
        laptop.setQuantity(laptop.getQuantity() - 1);
        System.out.println("After selling 1 unit: " + laptop.getQuantity());
        
        // Display specific item types
        System.out.println("\n--- Books Only ---");
        displayBooks(inventory);
        
        System.out.println("\n--- Electronics Only ---");
        displayElectronics(inventory);
        
        System.out.println("\n--- Clothing Only ---");
        displayClothing(inventory);
        
        // Calculate total inventory value
        double totalValue = calculateTotalValue(inventory);
        System.out.println("\n--- Inventory Statistics ---");
        System.out.println("Total Items: " + inventory.size());
        System.out.println("Total Inventory Value: Rs." + String.format("%.2f", totalValue));
    }
    
    public static void displayInventory(List<Item> items) {
        for (Item item : items) {
            System.out.print("ID: " + item.getId() + " | ");
            System.out.print("Name: " + item.getName() + " | ");
            System.out.print("Price: Rs." + item.getPrice() + " | ");
            System.out.print("Quantity: " + item.getQuantity());
            
            if (item instanceof Book) {
                Book book = (Book) item;
                System.out.print(" | Author: " + book.getAuthor());
            } else if (item instanceof Electronics) {
                Electronics electronics = (Electronics) item;
                System.out.print(" | Warranty: " + electronics.getWarranty() + " months");
            } else if (item instanceof Clothing) {
                Clothing clothing = (Clothing) item;
                System.out.print(" | Size: " + clothing.getSize());
            }
            System.out.println();
        }
    }
    
    public static void displayBooks(List<Item> items) {
        for (Item item : items) {
            if (item instanceof Book) {
                Book book = (Book) item;
                System.out.println("Book: " + book.getName() + " by " + book.getAuthor() + 
                                   " - Rs." + book.getPrice());
            }
        }
    }
    
    public static void displayElectronics(List<Item> items) {
        for (Item item : items) {
            if (item instanceof Electronics) {
                Electronics electronics = (Electronics) item;
                System.out.println("Electronics: " + electronics.getName() + 
                                   " - Rs." + electronics.getPrice() + 
                                   " (Warranty: " + electronics.getWarranty() + " months)");
            }
        }
    }
    
    public static void displayClothing(List<Item> items) {
        for (Item item : items) {
            if (item instanceof Clothing) {
                Clothing clothing = (Clothing) item;
                System.out.println("Clothing: " + clothing.getName() + 
                                   " - Size: " + clothing.getSize() + 
                                   " - Rs." + clothing.getPrice());
            }
        }
    }
    
    public static double calculateTotalValue(List<Item> items) {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}
