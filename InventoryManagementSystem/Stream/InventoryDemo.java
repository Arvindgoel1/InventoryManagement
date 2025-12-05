package InventoryManagementSystem.Stream;

import java.util.*;
import java.util.stream.*;

public class InventoryDemo {
    public static void main(String[] args) {
        List<Item> inventory = Arrays.asList(
                new Item(101, "Laptop"),
                new Item(102, "Mouse"),
                new Item(103, "Keyboard")
        );

        inventory.stream()
                .map(Item::getId)       // extract the IDs
                .forEach(System.out::println);  // print each ID
    }
}
