package engine;

import domain.Product;

public class FlatDiscountDeal implements DealEngine {

    private final double discountPercent;

    public FlatDiscountDeal(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculateFinalPrice(Product product, int groupSize) {
        return product.getPrice() * (1 - discountPercent / 100.0);
    }
}
