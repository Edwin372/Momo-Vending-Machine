package main.java.com.momo.momoTalent2021.enums;

public enum ProductType {

    COKE("Coke ", 10000),
    PEPSI("Pepsi", 10000),
    SODA("Soda ", 20000);

    private String name;
    private int price;

    ProductType(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

}
