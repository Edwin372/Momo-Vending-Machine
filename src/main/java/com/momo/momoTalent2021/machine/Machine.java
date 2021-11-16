package main.java.com.momo.momoTalent2021.machine;

import main.java.com.momo.momoTalent2021.enums.ProductType;
import main.java.com.momo.momoTalent2021.machine.controller.NoCoinInsertedState;
import main.java.com.momo.momoTalent2021.machine.inventory.Inventory;
import main.java.com.momo.momoTalent2021.machine.controller.Controller;
import main.java.com.momo.momoTalent2021.machine.inventory.ProductSpiral;

import java.util.HashMap;

public class Machine {
   private static Machine machine;
   private Controller controller;
   private Inventory inventory;

   private Machine() {
      this.inventory =  Inventory.getInventory();
      this.controller = new NoCoinInsertedState(this);
   }

   public void setControllerState(Controller controllerState) {
      this.controller = controllerState;
   }

   public static Machine getMachine() {
      Machine currentMachine = machine;
      if (currentMachine != null) {
         return currentMachine;
      }
      synchronized(Machine.class) {
         if (machine == null) {
            machine = new Machine();
         }
         return machine;
      }
   }
}
