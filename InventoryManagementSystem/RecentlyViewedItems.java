package InventoryManagementSystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecentlyViewedItems {
    private static final int MAX_SIZE = 3;
    private LinkedList<Item> items;

    public RecentlyViewedItems() {
        this.items = new LinkedList<>();
    }

    // Add item to recently viewed, handling re-visits
    public void addRecentlyViewedItem(Item item) {
        // Remove the item if already present (handles re-visiting)
        items.remove(item);
        
        // Add item to front (most recent)
        items.addFirst(item);
        
        // Check if size exceeds MAX_SIZE and remove last item
        if (items.size() > MAX_SIZE) {
            items.removeLast();
        }
    }

    // Get recently viewed items as a safe copy
    public List<Item> getRecentlyViewedItems() {
        return new ArrayList<>(items);
    }
}
