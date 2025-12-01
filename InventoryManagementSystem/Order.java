package InventoryManagementSystem;

public class Order implements Comparable<Order> {
    private String orderId;
    private boolean isExpress;

    public Order(String orderId, boolean isExpress) {
        this.orderId = orderId;
        this.isExpress = isExpress;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isExpress() {
        return isExpress;
    }

    public void setExpress(boolean express) {
        isExpress = express;
    }

    // Implement priority logic
    @Override
    public int compareTo(Order o) {
        // If both orders have same type (both express or both regular)
        if (this.isExpress == o.isExpress) {
            // Compare by orderId alphabetically
            return this.orderId.compareTo(o.orderId);
        } else {
            // Express order has higher priority
            // If this order is express, it should come first (return -1)
            // If other order is express, it should come first (return 1)
            return this.isExpress ? -1 : 1;
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", type=" + (isExpress ? "EXPRESS" : "REGULAR") +
                '}';
    }
}
