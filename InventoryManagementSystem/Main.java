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
        
        // Demonstrate Recently Viewed Items Tracker
        System.out.println("\n=== MILESTONE 3: RECENTLY VIEWED ITEMS TRACKER ===\n");
        
        RecentlyViewedItems recentlyViewed = new RecentlyViewedItems();
        
        // Create some items for demonstration
        Book book1 = new Book("B001", "Java Programming", 599.99, 10, "James Gosling");
        Book book2 = new Book("B002", "Data Structures", 449.50, 15, "Robert Sedgewick");
        Electronics laptop = new Electronics("E001", "Laptop", 45999.00, 5, 24);
        Clothing tshirt = new Clothing("C001", "T-Shirt", 299.99, 50, "L");
        Electronics phone = new Electronics("E002", "Smartphone", 25999.00, 12, 12);
        
        // View items in sequence
        System.out.println("--- Viewing Items in Sequence ---");
        
        recentlyViewed.addRecentlyViewedItem(book1);
        System.out.println("Viewed: " + book1.getName());
        displayRecentlyViewed(recentlyViewed);
        
        recentlyViewed.addRecentlyViewedItem(laptop);
        System.out.println("\nViewed: " + laptop.getName());
        displayRecentlyViewed(recentlyViewed);
        
        recentlyViewed.addRecentlyViewedItem(book2);
        System.out.println("\nViewed: " + book2.getName());
        displayRecentlyViewed(recentlyViewed);
        
        // Test capacity limit - adding 4th item should remove oldest
        System.out.println("\n--- Testing Capacity Limit (MAX_SIZE = 3) ---");
        recentlyViewed.addRecentlyViewedItem(tshirt);
        System.out.println("Viewed: " + tshirt.getName() + " (4th item)");
        System.out.println("Expected: Oldest item (Java Programming) should be removed");
        displayRecentlyViewed(recentlyViewed);
        
        // Test re-visiting existing item
        System.out.println("\n--- Testing Re-visit: Viewing Laptop Again ---");
        System.out.println("Before re-visit:");
        displayRecentlyViewed(recentlyViewed);
        
        recentlyViewed.addRecentlyViewedItem(laptop);
        System.out.println("\nAfter re-visiting Laptop:");
        System.out.println("Expected: Laptop moves to front, order changes but no item removed");
        displayRecentlyViewed(recentlyViewed);
        
        // Add another new item
        System.out.println("\n--- Adding New Item (Smartphone) ---");
        recentlyViewed.addRecentlyViewedItem(phone);
        System.out.println("Viewed: " + phone.getName());
        displayRecentlyViewed(recentlyViewed);
        
        // Test re-visiting middle item
        System.out.println("\n--- Re-visiting Middle Item (T-Shirt) ---");
        recentlyViewed.addRecentlyViewedItem(tshirt);
        System.out.println("Viewed: " + tshirt.getName() + " again");
        System.out.println("Expected: T-Shirt moves to front");
        displayRecentlyViewed(recentlyViewed);
        
        // Demonstrate Priority-Based Order Processing
        System.out.println("\n=== MILESTONE 4: PRIORITY-BASED ORDER PROCESSING ===\n");
        
        OrderProcessor orderProcessor = new OrderProcessor();
        
        // Add mixed orders (Regular and Express)
        System.out.println("--- Adding Orders ---");
        orderProcessor.addOrder(new Order("ORD005", false)); // Regular
        orderProcessor.addOrder(new Order("ORD001", true));  // Express
        orderProcessor.addOrder(new Order("ORD003", false)); // Regular
        orderProcessor.addOrder(new Order("ORD002", true));  // Express
        orderProcessor.addOrder(new Order("ORD004", false)); // Regular
        orderProcessor.addOrder(new Order("ORD006", true));  // Express
        
        System.out.println("\nTotal orders in queue: " + orderProcessor.getSize());
        
        // Process orders according to priority
        System.out.println("\n--- Processing Orders (Priority-Based) ---");
        System.out.println("Expected Order:");
        System.out.println("1. Express orders first (ORD001, ORD002, ORD006) - alphabetically");
        System.out.println("2. Regular orders next (ORD003, ORD004, ORD005) - alphabetically\n");
        
        int orderNumber = 1;
        while (orderProcessor.getSize() > 0) {
            Order processed = orderProcessor.processOrder();
            System.out.println(orderNumber + ". Processed: " + processed);
            orderNumber++;
        }
        
        // Test with another batch
        System.out.println("\n--- Testing Another Batch ---");
        orderProcessor.addOrder(new Order("ORD101", false)); // Regular
        orderProcessor.addOrder(new Order("ORD102", false)); // Regular
        orderProcessor.addOrder(new Order("ORD103", true));  // Express
        orderProcessor.addOrder(new Order("ORD104", false)); // Regular
        orderProcessor.addOrder(new Order("ORD105", true));  // Express
        
        System.out.println("\nPeek at next order: " + orderProcessor.peekNextOrder());
        System.out.println("Queue size: " + orderProcessor.getSize());
        
        System.out.println("\nProcessing all orders:");
        orderNumber = 1;
        while (orderProcessor.getSize() > 0) {
            Order processed = orderProcessor.processOrder();
            System.out.println(orderNumber + ". " + processed);
            orderNumber++;
        }
        
        // Test same type comparison
        System.out.println("\n--- Testing Same Type Priority (All Express) ---");
        orderProcessor.addOrder(new Order("ORD203", true)); // Express
        orderProcessor.addOrder(new Order("ORD201", true)); // Express
        orderProcessor.addOrder(new Order("ORD205", true)); // Express
        orderProcessor.addOrder(new Order("ORD202", true)); // Express
        orderProcessor.addOrder(new Order("ORD204", true)); // Express
        
        System.out.println("Expected: Alphabetical order (ORD201, ORD202, ORD203, ORD204, ORD205)\n");
        orderNumber = 1;
        while (orderProcessor.getSize() > 0) {
            Order processed = orderProcessor.processOrder();
            System.out.println(orderNumber + ". " + processed);
            orderNumber++;
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
        Item laptopItem = inventory.get(inventory.size() - 1); // Most expensive item
        System.out.println("Original price of " + laptopItem.getName() + ": Rs." + laptopItem.getPrice());
        laptopItem.setPrice(42999.00);
        System.out.println("Updated price: Rs." + laptopItem.getPrice());
        
        System.out.println("\nOriginal quantity: " + laptopItem.getQuantity());
        laptopItem.setQuantity(laptopItem.getQuantity() - 1);
        System.out.println("After selling 1 unit: " + laptopItem.getQuantity());
        
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
    
    public static void displayRecentlyViewed(RecentlyViewedItems recentlyViewed) {
        List<Item> items = recentlyViewed.getRecentlyViewedItems();
        System.out.println("Recently Viewed Items (" + items.size() + " items):");
        
        if (items.isEmpty()) {
            System.out.println("  (empty)");
            return;
        }
        
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.print("  " + (i + 1) + ". " + item.getName());
            
            if (item instanceof Book) {
                Book book = (Book) item;
                System.out.print(" by " + book.getAuthor());
            } else if (item instanceof Electronics) {
                Electronics electronics = (Electronics) item;
                System.out.print(" (Warranty: " + electronics.getWarranty() + " months)");
            } else if (item instanceof Clothing) {
                Clothing clothing = (Clothing) item;
                System.out.print(" (Size: " + clothing.getSize() + ")");
            }
            
            System.out.println(" - Rs." + item.getPrice());
        }
    }
}
