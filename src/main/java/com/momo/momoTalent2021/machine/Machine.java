package main.java.com.momo.momoTalent2021.machine;

import main.java.com.momo.momoTalent2021.machine.controller.NoCoinInsertedController;
import main.java.com.momo.momoTalent2021.machine.dispenseStrategy.DispenseStrategy;
import main.java.com.momo.momoTalent2021.machine.dispenseStrategy.NoPromoStrategy;
import main.java.com.momo.momoTalent2021.machine.dispenseStrategy.PromoAppliedStrategy;
import main.java.com.momo.momoTalent2021.machine.inventory.Inventory;
import main.java.com.momo.momoTalent2021.machine.controller.Controller;
import main.java.com.momo.momoTalent2021.machine.inventory.ProductSpiral;

import java.util.HashMap;

public class Machine  {
   private static Machine machine;
   private Controller controller;
   private Inventory inventory;
   private int budget;
   private DispenseStrategy dispenseStrategy;
   private HashMap<ProductSpiral, Integer> selectedProducts;

   private Machine() {
      this.selectedProducts = new HashMap<>();
      this.inventory =  Inventory.getInventory();
      this.controller = new NoCoinInsertedController(this);
      this.dispenseStrategy = new NoPromoStrategy(this);
   }

   public HashMap<ProductSpiral, Integer> getSelectedProducts() {
      return selectedProducts;
   }

   public int getBudget() {
      return budget;
   }

   public void setBudget(int budget) {
      this.budget = budget;
   }

   public DispenseStrategy getDispenseStrategy() {
      return dispenseStrategy;
   }

   public void setDispenseStrategy(DispenseStrategy dispenseStrategy) {
      this.dispenseStrategy = dispenseStrategy;
   }

   public void setControllerState(Controller controllerState) {
      this.controller = controllerState;
   }


   public void displayGUI() {
      inventory.displayGUI();
      controller.displayGUI();
      controller.displayMessage();
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

   public Inventory getInventory() {
      return inventory;
   }

   public Controller getController() {return controller;}
}
