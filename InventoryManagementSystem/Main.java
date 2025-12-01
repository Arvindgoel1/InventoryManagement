package InventoryManagementSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INVENTORY MANAGEMENT SYSTEM ===\n");
        
        // Demonstrate Generic Inventory Class
        System.out.println("=== MILESTONE 2: GENERIC INVENTORY CLASS ===\n");
        
        // Create separate inventories for different item types
        Inventory<Book> bookInventory = new Inventory<>();
        Inventory<Clothing> clothingInventory = new Inventory<>();
        Inventory<Electronics> electronicsInventory = new Inventory<>();
        
        try {
            // Add Books to Book Inventory
            System.out.println("--- Adding Books ---");
            bookInventory.add(new Book("B001", "Java Programming", 599.99, 10, "James Gosling"));
            System.out.println("Added: Java Programming");
            bookInventory.add(new Book("B002", "Data Structures", 449.50, 15, "Robert Sedgewick"));
            System.out.println("Added: Data Structures");
            bookInventory.add(new Book("B003", "Design Patterns", 750.00, 8, "Gang of Four"));
            System.out.println("Added: Design Patterns");
            
            // Add Clothing to Clothing Inventory
            System.out.println("\n--- Adding Clothing ---");
            clothingInventory.add(new Clothing("C001", "T-Shirt", 299.99, 50, "L"));
            System.out.println("Added: T-Shirt");
            clothingInventory.add(new Clothing("C002", "Jeans", 899.00, 30, "M"));
            System.out.println("Added: Jeans");
            clothingInventory.add(new Clothing("C003", "Jacket", 1499.99, 20, "XL"));
            System.out.println("Added: Jacket");
            
            // Add Electronics to Electronics Inventory
            System.out.println("\n--- Adding Electronics ---");
            electronicsInventory.add(new Electronics("E001", "Laptop", 45999.00, 5, 24));
            System.out.println("Added: Laptop");
            electronicsInventory.add(new Electronics("E002", "Smartphone", 25999.00, 12, 12));
            System.out.println("Added: Smartphone");
            electronicsInventory.add(new Electronics("E003", "Headphones", 2999.00, 25, 6));
            System.out.println("Added: Headphones");
            
            // Test Exception Handling: Negative Quantity
            System.out.println("\n--- Testing Invalid Quantity Exception ---");
            try {
                bookInventory.add(new Book("B004", "Invalid Book", 500.00, -5, "Unknown Author"));
            } catch (InvalidQuantityException e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
            
            // Test Exception Handling: Duplicate ID
            System.out.println("\n--- Testing Duplicate Item Exception ---");
            try {
                bookInventory.add(new Book("B001", "Duplicate Book", 300.00, 5, "Test Author"));
            } catch (DuplicateItemException e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
            
            // Demonstrate Get Method
            System.out.println("\n--- Get Single Item by ID ---");
            Book javaBook = bookInventory.get("B001");
            if (javaBook != null) {
                System.out.println("Retrieved: " + javaBook.getName() + " by " + javaBook.getAuthor());
            }
            
            // Demonstrate GetAll Method
            System.out.println("\n--- Get All Books ---");
            List<Book> allBooks = bookInventory.getAll();
            for (Book book : allBooks) {
                System.out.println("- " + book.getName() + " by " + book.getAuthor() + " (Qty: " + book.getQuantity() + ")");
            }
            
            System.out.println("\n--- Get All Clothing ---");
            List<Clothing> allClothing = clothingInventory.getAll();
            for (Clothing clothing : allClothing) {
                System.out.println("- " + clothing.getName() + " Size: " + clothing.getSize() + " (Qty: " + clothing.getQuantity() + ")");
            }
            
            System.out.println("\n--- Get All Electronics ---");
            List<Electronics> allElectronics = electronicsInventory.getAll();
            for (Electronics electronics : allElectronics) {
                System.out.println("- " + electronics.getName() + " Warranty: " + electronics.getWarranty() + " months (Qty: " + electronics.getQuantity() + ")");
            }
            
            // Demonstrate Remove Method
            System.out.println("\n--- Testing Remove Method ---");
            Book bookToRemove = bookInventory.get("B002");
            if (bookToRemove != null) {
                System.out.println("Removing: " + bookToRemove.getName());
                bookInventory.remove(bookToRemove);
                System.out.println("Books remaining: " + bookInventory.getAll().size());
            }
            
        } catch (InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DuplicateItemException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== ORIGINAL DEMONSTRATION (Using List) ===\n");
        
        // Original demonstration using List
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
