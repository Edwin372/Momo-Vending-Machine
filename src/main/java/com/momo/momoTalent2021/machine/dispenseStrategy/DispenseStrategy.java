package main.java.com.momo.momoTalent2021.machine.dispenseStrategy;

import main.java.com.momo.momoTalent2021.machine.Machine;
import main.java.com.momo.momoTalent2021.machine.inventory.ProductSpiral;

import java.util.HashMap;

public abstract class DispenseStrategy {
    protected Machine machine;

    public DispenseStrategy(Machine machine) {
        this.machine = machine;
    }

    public void execute() {
        HashMap<ProductSpiral, Integer> selectedProducts = machine.getSelectedProducts();
        for (ProductSpiral productSpiral: selectedProducts.keySet()) {
            for (int i = 0; i < selectedProducts.get(productSpiral); i++) {
                productSpiral.dispenseProduct();
            }
        }
        System.out.println("Please Take your products in the pick-up box and receive your changes");
        System.out.println("Here is your change: " + machine.getBudget());
    }


}
