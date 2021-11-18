package main.java.com.momo.momoTalent2021.machine.controller;

import main.java.com.momo.momoTalent2021.enums.Coin;
import main.java.com.momo.momoTalent2021.exceptions.NotEnoughAvailableProductException;
import main.java.com.momo.momoTalent2021.exceptions.UnaffordableException;
import main.java.com.momo.momoTalent2021.machine.Machine;
import main.java.com.momo.momoTalent2021.machine.inventory.ProductSpiral;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class CoinInsertedAndProductSelectedController extends Controller {

    public CoinInsertedAndProductSelectedController(Machine machine) {
        super(machine);
    }

    public void displaySelectedProduct() {
        HashMap<ProductSpiral, Integer> selectedProducts = machine.getSelectedProducts();
        for (ProductSpiral productSpiral: selectedProducts.keySet()) {
            System.out.print(productSpiral.getProductType().getName() + ": ");
            System.out.println(selectedProducts.get(productSpiral));
        }
    }



    @Override
    public void cancelAndRefund() {
        System.out.println("Here is your money:" + machine.getBudget());
        machine.setControllerState(new NoCoinInsertedController(machine));
    }

    @Override
    public void dispenseProduct() {
        int totalCost = computeTotalCost();
        machine.setBudget(machine.getBudget() - totalCost);
        System.out.println("-----------------------------------BILL---------------------------------------- ");
        displaySelectedProduct();
        System.out.println("Total cost: " + totalCost);
        machine.getDispenseStrategy().execute();
        machine.setControllerState(new NoCoinInsertedController(machine));
    }

    @Override
    public void displayMessage() {
        super.displayMessage();
        System.out.println("Your current budget: " + machine.getBudget());
        System.out.println("Selected products: ");
        displaySelectedProduct();
        System.out.println("Total cost: " + computeTotalCost());
        System.out.println("You can insert more coin (I), choose another product (P) or press (Y) to dispense selected products.");
        System.out.print("Your input: ");
    }

    @Override
    public void selectProduct() {
        HashMap<ProductSpiral, Integer> selectedProducts = machine.getSelectedProducts();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose your product (1-3): ");
        String spiralCode = scanner.next();
        ProductSpiral selectingProduct = machine.getInventory().selectProductSpiral(Integer.parseInt(spiralCode));
        int currentTotalCost = computeTotalCost() + selectingProduct.getProductType().getPrice();

        if (!isAffordable(currentTotalCost)) {
            throw new UnaffordableException();
        }

        if(selectedProducts.containsKey(selectingProduct)) {
            int currentSelectingProductAmount = selectedProducts.get(selectingProduct);

            if (!selectingProduct.isAvailable(currentSelectingProductAmount + 1)) {
                throw new NotEnoughAvailableProductException();
            }

            selectedProducts.put(selectingProduct, currentSelectingProductAmount + 1);
        }else {
            selectedProducts.put(selectingProduct, 1);
        }
    }
}
