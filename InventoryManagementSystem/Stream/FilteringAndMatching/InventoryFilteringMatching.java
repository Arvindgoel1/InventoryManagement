package InventoryManagementSystem.Stream.FilteringAndMatching;



import java.util.*;
import java.util.stream.*;

public class InventoryFilteringMatching {
    public static void main(String[] args) {
        List<Item> inventory = Arrays.asList(
                new Item("Laptop", 1500, 5),
                new Item("Mouse", 25, 0),
                new Item("Keyboard", 50, 10),
                new Item("Monitor", 200, 3),
                new Item("Printer", 1200, 2)
        );

        // 1️⃣ Items priced above 1000
        System.out.println("Items priced above 1000:");
        inventory.stream()
                .filter(item -> item.getPrice() > 1000)
                .map(Item::getName)
                .forEach(System.out::println);

        // 2️⃣ Any item with quantity 0
        boolean anyZeroQuantity = inventory.stream()
                .anyMatch(item -> item.getQuantity() == 0);
        System.out.println("\nAny item with quantity 0? " + anyZeroQuantity);

        // 3️⃣ All items have price > 0
        boolean allPricePositive = inventory.stream()
                .allMatch(item -> item.getPrice() > 0);
        System.out.println("All items have price > 0? " + allPricePositive);

        // 4️⃣ No item has negative quantity
        boolean noneNegativeQuantity = inventory.stream()
                .noneMatch(item -> item.getQuantity() < 0);
        System.out.println("No item has negative quantity? " + noneNegativeQuantity);
    }
}

