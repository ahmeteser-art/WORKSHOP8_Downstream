package Wworksop8.chasacademy;

public class OrderItem {
    private final String name;
    private final String category; // "Mat", "Dryck", "Desert"
    private final double price;

    public OrderItem(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s (%s - %.2f kr)", name, category, price);
    }
}
