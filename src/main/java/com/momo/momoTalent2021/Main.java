package main.java.com.momo.momoTalent2021;

import main.java.com.momo.momoTalent2021.machine.Machine;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        Machine machine = Machine.getMachine();
        PromoSchemeManager promoSchemeManager = new PromoSchemeManager();

        boolean machineOn = true;
        while (machineOn) {
            System.out.println("------------------------------------------------------------------------------- ");
            promoSchemeManager.start();
            machine.displayGUI();
            String input = scanner.next();
            try {
                switch (input.toUpperCase(Locale.ROOT)) {
                    case "I":{
                        machine.getController().insertCoin();
                        break;
                    }
                    case "Y":{
                        machine.getController().dispenseProduct();
                        break;
                    }
                    case "X":{
                        machine.getController().cancelAndRefund();
                        break;
                    }
                    case "P":{
                        machine.getController().selectProduct();
                        break;
                    }
                    case "Q": {
                        machineOn = false;
                        break;
                    }
                    default: {
                        System.out.println("Invalid input");
                        break;
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
