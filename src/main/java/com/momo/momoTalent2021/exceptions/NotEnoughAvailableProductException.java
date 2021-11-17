package main.java.com.momo.momoTalent2021.exceptions;

public class NotEnoughAvailableProductException extends RuntimeException {
    public NotEnoughAvailableProductException() {
        super("This product dose not have enough quantity available, please choose another instead.");
    }
}
