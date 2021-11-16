package main.java.com.momo.momoTalent2021.exceptions;

public class NoCoinInsertedException extends RuntimeException {
    public NoCoinInsertedException() {
        super("Please insert coin to continue");
    }
}
