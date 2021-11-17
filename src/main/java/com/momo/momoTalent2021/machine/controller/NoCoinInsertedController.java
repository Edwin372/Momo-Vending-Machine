package main.java.com.momo.momoTalent2021.machine.controller;

import main.java.com.momo.momoTalent2021.enums.Coin;
import main.java.com.momo.momoTalent2021.exceptions.NoCoinInsertedException;
import main.java.com.momo.momoTalent2021.machine.Machine;

public class NoCoinInsertedController extends Controller {

    public NoCoinInsertedController(Machine machine) {
        super(machine);
        machine.setBudget(0);
        machine.getSelectedProducts().clear();
    }

    @Override
    public void displayMessage() {
        super.displayMessage();
        System.out.println("Your budget is empty, please press (I) to insert coin");
        System.out.print("Your input: ");
    }

    @Override
    public void insertCoin() {
        super.insertCoin();
        machine.setControllerState(new CoinInsertedAndNoProductSelectedController(machine));
    }

    @Override
    public void cancelAndRefund() {
        throw new NoCoinInsertedException();
    }

    @Override
    public void confirmAndDispenseProduct() {
        throw new NoCoinInsertedException();
    }

    @Override
    public void selectProduct() {
        throw new NoCoinInsertedException();
    }
}
