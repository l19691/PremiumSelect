package engine;

import domain.Product;

public class ThresholdUnlockDeal implements DealEngine {

    private final int threshold;
    private final double discountPercent;

    public ThresholdUnlockDeal(int threshold, double discountPercent) {
        this.threshold = threshold;
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculateFinalPrice(Product product, int groupSize) {
        if (groupSize >= threshold) {
            return product.getPrice() * (1 - discountPercent / 100.0);
        }
        return product.getPrice();
    }
}

