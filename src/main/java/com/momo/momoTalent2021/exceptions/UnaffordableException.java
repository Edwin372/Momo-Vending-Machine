package main.java.com.momo.momoTalent2021.exceptions;

public class UnaffordableException extends RuntimeException{
    public UnaffordableException() {
        super("You don't have enough money, please insert more coin");
    }
}
