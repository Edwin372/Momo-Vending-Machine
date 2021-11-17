package main.java.com.momo.momoTalent2021.machine.controller;

import main.java.com.momo.momoTalent2021.enums.Coin;
import main.java.com.momo.momoTalent2021.exceptions.NotEnoughAvailableProductException;
import main.java.com.momo.momoTalent2021.exceptions.UnaffordableException;
import main.java.com.momo.momoTalent2021.machine.Machine;
import main.java.com.momo.momoTalent2021.machine.inventory.ProductSpiral;

import java.util.HashMap;
import java.util.Scanner;

public class CoinInsertedAndProductSelectedController extends Controller {

    public CoinInsertedAndProductSelectedController(Machine machine) {
        super(machine);
    }

    public void displaySelectedProduct() {
        HashMap<ProductSpiral, Integer> selectedProducts = machine.getSelectedProducts();
        for (ProductSpiral productSpiral: selectedProducts.keySet()) {
            System.out.print(productSpiral.getProductType().getName()+ ": ");
            System.out.println(selectedProducts.get(productSpiral));
        }
    }

    @Override
    public void cancelAndRefund() {
        System.out.println("Here is your money:" + machine.getBudget());
        machine.setControllerState(new NoCoinInsertedController(machine));
    }

    @Override
    public void confirmAndDispenseProduct() {
        try {
            HashMap<ProductSpiral, Integer> selectedProducts = machine.getSelectedProducts();
            machine.setBudget(machine.getBudget() - computeTotalCost());
            System.out.println("Here are your products");
            displaySelectedProduct();
            for (ProductSpiral productSpiral: selectedProducts.keySet()) {
                for (int i = 0; i < selectedProducts.get(productSpiral); i++) {
                    productSpiral.dispenseProduct();
                }
            }
            System.out.println("Please Take your products in the dispense box and receive your changes");
            System.out.println("Here is your changes" + machine.getBudget());
            machine.setControllerState(new NoCoinInsertedController(machine));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayMessage() {
        super.displayMessage();
        System.out.println("Your current budget: " + machine.getBudget());
        System.out.println("Selected products: ");
        displaySelectedProduct();
        System.out.println("Total cost: " + computeTotalCost());
        System.out.println("You can choose another product or insert more coin");
        System.out.print("Your input: ");
    }

    @Override
    public void selectProduct() {
        HashMap<ProductSpiral, Integer> selectedProducts = machine.getSelectedProducts();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose your product (1-3): ");
        String spiralCode = scanner.next();
        ProductSpiral selectingProduct = machine.getInventory().selectProductType(Integer.parseInt(spiralCode));
        int currentTotalCost = computeTotalCost() + selectingProduct.getProductType().getPrice();

        if (!isAffordable(currentTotalCost)) {
            throw new UnaffordableException();
        }

        if(selectedProducts.containsKey(selectingProduct)) {
            int currentAmount = selectedProducts.get(selectingProduct);

            if (!selectingProduct.isAvailable(currentAmount + 1)) {
                throw new NotEnoughAvailableProductException();
            }

            selectedProducts.put(selectingProduct, currentAmount + 1);
        }else {
            selectedProducts.put(selectingProduct, 1);
        }
    }
}
