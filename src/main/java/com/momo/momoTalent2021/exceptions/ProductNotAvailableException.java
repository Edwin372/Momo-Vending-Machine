package main.java.com.momo.momoTalent2021.exceptions;

public class ProductNotAvailableException extends RuntimeException{

    public ProductNotAvailableException() {
        super("This product is not available, please comeback later");
    }
}
