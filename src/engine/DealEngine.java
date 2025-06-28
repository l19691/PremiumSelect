package engine;

import domain.Product;

public interface DealEngine {
    double calculateFinalPrice(Product product, int groupSize);
}
