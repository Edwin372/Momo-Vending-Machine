package main.java.com.momo.momoTalent2021.exceptions;

public class NoChoseProductException extends RuntimeException{
    public NoChoseProductException() {
        super("You didn't choose any product");
    }
}
