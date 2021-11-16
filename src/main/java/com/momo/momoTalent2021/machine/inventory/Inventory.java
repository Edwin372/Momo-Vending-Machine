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
        productMenu.put(1, new ProductSpiral(ProductType.COKE));
        productMenu.put(2, new ProductSpiral(ProductType.PEPSI));
        productMenu.put(3, new ProductSpiral(ProductType.SODA));
        refillAll();
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

    public ProductSpiral selectProductType(int spiralCode) {
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

}
