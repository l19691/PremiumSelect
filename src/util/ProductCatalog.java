package util;

import domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductCatalog {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String name) {
        products.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public List<Product> getAll() {
        return products;
    }

    public List<Product> filterByCategory(String keyword) {
        return products.stream()
                .filter(p -> p.getTags().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Product getProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void preloadSampleProducts() {
        addProduct(new Product("Crosskix Shoes", 60.0, Set.of("shoes", "sport")));
        addProduct(new Product("Organic Olive Oil", 12.5, Set.of("food", "vegan")));
        addProduct(new Product("Za’atar Jar", 6.0, Set.of("spice", "food", "levant")));
        addProduct(new Product("Murano Bowl", 89.0, Set.of("glass", "luxury", "decor")));
    }
}

