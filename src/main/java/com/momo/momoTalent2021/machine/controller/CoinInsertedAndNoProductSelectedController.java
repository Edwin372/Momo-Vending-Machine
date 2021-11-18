package main.java.com.momo.momoTalent2021.machine.controller;

import main.java.com.momo.momoTalent2021.enums.Coin;
import main.java.com.momo.momoTalent2021.exceptions.NoChosenProductException;
import main.java.com.momo.momoTalent2021.exceptions.UnaffordableException;
import main.java.com.momo.momoTalent2021.machine.Machine;
import main.java.com.momo.momoTalent2021.machine.inventory.ProductSpiral;

import java.util.Scanner;

public class CoinInsertedAndNoProductSelectedController extends Controller {

    public CoinInsertedAndNoProductSelectedController(Machine machine) {
        super(machine);
    }


    @Override
    public void cancelAndRefund() {
        System.out.println("Here is your money:" + machine.getBudget());
        machine.setControllerState(new NoCoinInsertedController(machine));
    }

    @Override
    public void dispenseProduct() {
        throw new NoChosenProductException();
    }

    @Override
    public void displayMessage() {
        super.displayMessage();
        System.out.println("Your current budget: " + machine.getBudget());
        System.out.println("Please choose one product (P) or insert more coin (I).");
        System.out.print("Your input: ");
    }

    @Override
    public void selectProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose your product (1-3): ");
        String spiralCode = scanner.next();
        ProductSpiral selectingProduct = machine.getInventory().selectProductSpiral(Integer.parseInt(spiralCode));

        if (isAffordable(selectingProduct.getProductType().getPrice())) {
            machine.getSelectedProducts().put(selectingProduct, 1);
            machine.setControllerState(new CoinInsertedAndProductSelectedController(machine));
        } else {
            throw new UnaffordableException();
        }
    }
}
