package main.java.com.momo.momoTalent2021.machine.inventory;
import main.java.com.momo.momoTalent2021.enums.ProductType;

public class Product {
    private ProductType productType;
    // There might be more fields here

    public Product(ProductType productType) {
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productType == product.productType;
    }

    @Override
    public String toString() {
        return productType.getName();
    }
}
