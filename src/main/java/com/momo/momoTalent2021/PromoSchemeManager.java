package main.java.com.momo.momoTalent2021;

import main.java.com.momo.momoTalent2021.machine.Machine;
import main.java.com.momo.momoTalent2021.machine.dispenseStrategy.PromoAppliedStrategy;

public class PromoSchemeManager extends Thread {
    private Thread t;
    Machine machine = Machine.getMachine();

    @Override
    public void run() {
        while (machine.getDispenseStrategy().getClass() == PromoAppliedStrategy.class) {
            try {
                // If the limit cannot be reached for a day, the win rate of the next day will be increased by 50%
                t.sleep(86400000);
                if (machine.getDispenseStrategy().getClass() == PromoAppliedStrategy.class) {
                    PromoAppliedStrategy dispenseStrategy = (PromoAppliedStrategy) machine.getDispenseStrategy();

                    dispenseStrategy.increaseWinRate();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



    public void start( ) {
        if (t == null || t.getState() == State.TERMINATED) {
            t = new Thread(this );
            t.start();
        }
    }
}