package main.java.com.momo.momoTalent2021.machine.inventory;
import main.java.com.momo.momoTalent2021.enums.ProductType;
import main.java.com.momo.momoTalent2021.exceptions.ProductNotAvailableException;
import main.java.com.momo.momoTalent2021.machine.Machine;

import java.util.HashMap;

public class Inventory {
    private static Inventory inventory;
    private HashMap<Integer, ProductSpiral> productMenu;

    private Inventory() {
        this.productMenu = new HashMap<>();
        ProductType [] productTypes = ProductType.values();
        for (int i = 0; i < productTypes.length; i++) {
            productMenu.put( i + 1, new ProductSpiral(productTypes[i]));
        }
        this.refillAll();
    }

    public static Inventory getInventory() {
        Inventory currentMachine = inventory;
        if (currentMachine != null) {
            return currentMachine;
        }
        synchronized(Machine.class) {
            if (inventory == null) {
                inventory = new Inventory();
            }
            return inventory;
        }
    }

    public ProductSpiral selectProductSpiral(int spiralCode) {
       ProductSpiral selectedSpiral = productMenu.get(spiralCode);
       if (!selectedSpiral.isEmpty()){
           return selectedSpiral;
       }
       else {
           throw new ProductNotAvailableException();
       }
    }

    public void refillAll() {
        for (ProductSpiral spiral: productMenu.values()) {
            spiral.refill();
        }
    }

    public HashMap<Integer, ProductSpiral> getProductMenu() {
        return productMenu;
    }

    public void displayGUI() {
        System.out.println("====================================================================");
        System.out.println("|                             Inventory                            |");
        System.out.println("====================================================================");
        for (Integer code: productMenu.keySet()) {
            ProductSpiral productSpiral = productMenu.get(code);
            System.out.print("|      Name: " + productSpiral.getProductType().getName() + "; ");
            System.out.print("Price: " + productSpiral.getProductType().getPrice() + "; ");
            System.out.print("Remain product: " + productSpiral.getRemainProduct() + "; ");
            System.out.println("Code: " + code+ "      |");
        }
        System.out.println("====================================================================");

    }


}
