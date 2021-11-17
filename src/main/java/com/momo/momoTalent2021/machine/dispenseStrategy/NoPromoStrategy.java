package main.java.com.momo.momoTalent2021.machine.dispenseStrategy;
import main.java.com.momo.momoTalent2021.machine.Machine;
import main.java.com.momo.momoTalent2021.machine.inventory.ProductSpiral;

import java.util.Collection;
import java.util.Locale;
import java.util.Scanner;

public class NoPromoStrategy extends DispenseStrategy {
    public NoPromoStrategy(Machine machine) {
        super(machine);
        Collection<ProductSpiral> productSpirals = machine.getInventory().getProductMenu().values();
        for (ProductSpiral productSpiral: productSpirals) {
            productSpiral.setConsecutiveChosenCount(0);
        }
    }

    public void updateConsecutiveCounts() {
        Collection<ProductSpiral> productSpirals = machine.getInventory().getProductMenu().values();
        for (ProductSpiral productSpiral: productSpirals) {
            if (machine.getSelectedProducts().containsKey(productSpiral)) {
                productSpiral.setConsecutiveChosenCount(productSpiral.getConsecutiveChosenCount() + 1);
            } else {
                productSpiral.setConsecutiveChosenCount(0);
            }
        }
    }

    public boolean isPromoConditionSatisfied() {
        Collection<ProductSpiral> productSpirals = machine.getInventory().getProductMenu().values();
        for (ProductSpiral productSpiral: productSpirals) {
            if( productSpiral.getConsecutiveChosenCount() == 3) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        super.execute();
        Scanner scanner = new Scanner(System.in);
        String gotStuff = "N";
        this.updateConsecutiveCounts();
        if (isPromoConditionSatisfied()) {
            machine.setDispenseStrategy(new PromoAppliedStrategy(machine));
        }

        do {
            System.out.println("Got your stuff? (Y/N) ");
            gotStuff = scanner.next().toUpperCase(Locale.ROOT);
        } while (gotStuff.equals("N"));
    }

}
