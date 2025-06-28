package data;

import domain.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final String username;
    private final List<Product> items;
    private final double total;
    private final LocalDateTime timestamp;

    public Order(String username, List<Product> items, double total) {
        this.username = username;
        this.items = items;
        this.total = total;
        this.timestamp = LocalDateTime.now();
    }

    public void saveToFile() {
        String filename = "orders_" + username + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("🧾 Order placed by " + username + " at " + timestamp + "\n");
            for (Product p : items) {
                writer.write("  - " + p.getName() + " (€" + p.getPrice() + ")\n");
            }
            writer.write("Total: €" + String.format("%.2f", total) + "\n\n");
        } catch (IOException e) {
            System.out.println("Failed to save order: " + e.getMessage());
        }
    }

    public double getTotal() {
        return total;
    }

    public String getUsername() {
        return username;
    }

    public List<Product> getItems() {
        return items;
    }
}

