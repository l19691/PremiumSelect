package cart;

import domain.Product;
import engine.DealEngine;

import java.util.ArrayList;
import java.util.List;

public class GroupCart {
    private final List<Product> products = new ArrayList<>();
    private DealEngine dealStrategy;
    private int groupSize;

    public GroupCart(DealEngine dealStrategy, int groupSize) {
        this.dealStrategy = dealStrategy;
        this.groupSize = groupSize;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double calculateTotal() {
        return products.stream()
            .mapToDouble(p -> dealStrategy.calculateFinalPrice(p, groupSize))
            .sum();
    }

    public void setDealStrategy(DealEngine newStrategy) {
        this.dealStrategy = newStrategy;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public CartIterator getIterator() {
        return new CartIterator(products);
    }
}

