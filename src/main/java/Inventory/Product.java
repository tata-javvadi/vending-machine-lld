package Inventory;

public class Product {
    private final String name;
    private final int id;
    private final double price;

    public Product(String name, int id, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("Product ID must be positive.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative.");
        }
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " (ID: " + id + ", price: " + price + ")";
    }
}
