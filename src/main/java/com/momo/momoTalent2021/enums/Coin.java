package main.java.com.momo.momoTalent2021.enums;

public enum Coin {
    TEN_THOUSAND_VND(10000),
    TWENTY_THOUSAND_VND(20000),
    FIFTY_THOUSAND_VND(50000),
    ONE_HUNDRED_THOUSAND_VND(100000),
    TWO_HUNDRED_THOUSAND_VND(200000);

    private int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

}
