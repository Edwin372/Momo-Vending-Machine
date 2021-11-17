package main.java.com.momo.momoTalent2021.exceptions;

public class InvalidCoinException extends RuntimeException{
    public InvalidCoinException() {
        super("Invalid coin");
    }
}
