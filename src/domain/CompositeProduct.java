package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CompositeProduct extends Product {
    private List<Product> components = new ArrayList<>();

    public CompositeProduct(String name, double price, Set<String> tags) {
        super(name, price, tags);
    }

    public void addProduct(Product product) {
        components.add(product);
    }

    public List<Product> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Bundle] ").append(getName()).append(" (€").append(getPrice()).append(") ");
        sb.append(getTags()).append("\nIncludes:\n");
        for (Product p : components) {
            sb.append("  - ").append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}
