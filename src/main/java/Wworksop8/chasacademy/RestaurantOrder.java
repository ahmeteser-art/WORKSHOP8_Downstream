package Wworksop8.chasacademy;

import java.util.List;

public class RestaurantOrder {
    public enum OrderType { DINE_IN, TAKEAWAY, DELIVERY }

    private final String orderId;
    private final String customerName;
    private final OrderType orderType;
    private final List<OrderItem> items;
    private final boolean paid;

    public RestaurantOrder(String orderId, String customerName, OrderType orderType, List<OrderItem> items, boolean paid) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderType = orderType;
        this.items = items;
        this.paid = paid;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public OrderType getOrderType() { return orderType; }
    public List<OrderItem> getItems() { return items; }
    public boolean isPaid() { return paid; }

    public double getTotalPrice() {
        return items.stream().mapToDouble(OrderItem::getPrice).sum();
    }

    @Override
    public String toString() {
        return String.format("Order %s [%s] - Kund: %s | Artiklar: %d | Totalt: %.2f kr | %s",
                orderId, orderType, customerName, items.size(), getTotalPrice(), paid ? "Betald" : "OBETALD");
    }
}
