package main.java.com.momo.momoTalent2021.exceptions;

public class NoChosenProductException extends RuntimeException{
    public NoChosenProductException() {
        super("You didn't choose any product");
    }
}
