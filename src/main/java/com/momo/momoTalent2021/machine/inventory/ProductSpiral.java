package main.java.com.momo.momoTalent2021.machine.inventory;

import main.java.com.momo.momoTalent2021.enums.ProductType;

import java.util.Queue;

public class ProductSpiral {
    static final int CAPACITY = 10;
    private Queue<Product> products;
    private int consecutiveChosenCount;
    private ProductType productType;

    public ProductSpiral(ProductType productType) {
        this.consecutiveChosenCount = 0;
        this.productType = productType;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int getRemainProduct() {
        return products.size();
    }

    public Product dispenseProduct() {
        consecutiveChosenCount += 1;
        return products.poll();
    }

    public void resetConsecutiveChosenCount() {
        consecutiveChosenCount = 0;
    }

    public int getConsecutiveChosenCount() {
        return consecutiveChosenCount;
    }

    public void setConsecutiveChosenCount(int consecutiveChosenCount) {
        this.consecutiveChosenCount = consecutiveChosenCount;
    }

    public void refill() {
        for (int i = products.size() - 1 ; i < CAPACITY; i++) {
           Product product = new Product(productType);
           products.add(product);
        }
    }

}
