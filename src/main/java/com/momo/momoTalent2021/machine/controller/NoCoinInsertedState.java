package main.java.com.momo.momoTalent2021.machine.controller;

import main.java.com.momo.momoTalent2021.machine.Machine;
import main.java.com.momo.momoTalent2021.machine.promoProgram.DispenseStrategy;

public class NoCoinInsertedState extends Controller {

    public NoCoinInsertedState(Machine machine) {
        super(machine);
    }

    @Override
    public void insertCoin() {
    }

    @Override
    public void cancelAndRefund() {
    }

    @Override
    public void confirmAndDispenseProduct() {
    }
}
