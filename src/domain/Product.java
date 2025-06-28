package domain;

import java.util.Set;

public class Product {
    private String name;
    private double price;
    private Set<String> tags;

    public Product(String name, double price, Set<String> tags) {
        this.name = name;
        this.price = price;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return name + " (€" + price + ") " + tags;
    }
}
