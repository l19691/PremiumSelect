package cart;

import domain.Product;

import java.util.List;

public class CartIterator {
    private final List<Product> products;
    private int index = 0;

    public CartIterator(List<Product> products) {
        this.products = products;
    }

    public boolean hasNext() {
        return index < products.size();
    }

    public Product next() {
        return products.get(index++);
    }

    public void reset() {
        index = 0;
    }
}

