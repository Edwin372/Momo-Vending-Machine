package main.java.com.momo.momoTalent2021.machine.inventory;

import main.java.com.momo.momoTalent2021.enums.ProductType;

import java.util.Objects;

public class Product {
    ProductType productType;
    // There might be more fields here

    public Product(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productType == product.productType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productType=" + productType +
                '}';
    }
}
