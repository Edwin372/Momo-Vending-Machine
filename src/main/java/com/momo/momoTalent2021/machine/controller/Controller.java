package main.java.com.momo.momoTalent2021.machine.controller;

import main.java.com.momo.momoTalent2021.machine.Machine;
import main.java.com.momo.momoTalent2021.machine.promoProgram.DispenseStrategy;

public  abstract class Controller {


    protected DispenseStrategy dispenseStrategy;
    protected Machine machine;


    Controller(Machine machine) {
        this.machine = machine;
    }

    public DispenseStrategy getDispenseStrategy() {
        return dispenseStrategy;
    }

    public void setDispenseStrategy(DispenseStrategy dispenseStrategy) {
        this.dispenseStrategy = dispenseStrategy;
    }

    public abstract void insertCoin();
    public abstract void cancelAndRefund();
    public abstract void confirmAndDispenseProduct();
}
