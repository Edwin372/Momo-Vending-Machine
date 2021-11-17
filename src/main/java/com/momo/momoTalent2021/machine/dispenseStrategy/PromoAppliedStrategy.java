package main.java.com.momo.momoTalent2021.machine.dispenseStrategy;


import main.java.com.momo.momoTalent2021.enums.ProductType;
import main.java.com.momo.momoTalent2021.machine.Machine;
import main.java.com.momo.momoTalent2021.machine.inventory.Product;
import main.java.com.momo.momoTalent2021.machine.inventory.ProductSpiral;

import java.util.*;

public class PromoAppliedStrategy extends DispenseStrategy  {
    private final int PROMO_LIMIT = 50000;
    private final int INCREASE_RATE = 50;
    private List<ProductType> givenAwayProducts;
    private int winRate;
    SplittableRandom random = new SplittableRandom();

    public PromoAppliedStrategy(Machine machine) {
        super(machine);
        this.givenAwayProducts = new ArrayList<>();
        this.winRate = 10;
    }

    private Product getLuckyProduct() {
        HashMap<Integer, ProductSpiral> productMenu = machine.getInventory().getProductMenu();
        int luckyProductSpiralCode = random.nextInt(1, productMenu.size() + 2);
        ProductSpiral giveAwayProduct = productMenu.get(luckyProductSpiralCode);
        while (giveAwayProduct.getProductType().getPrice() > getRemainLimit()) {
            luckyProductSpiralCode = random.nextInt(1, productMenu.size() + 2);
            giveAwayProduct = productMenu.get(luckyProductSpiralCode);
        }
        return giveAwayProduct.dispenseProduct();
    }

    @Override
    public void execute() {
        super.execute();
        Scanner scanner = new Scanner(System.in);
        String gotStuff = "N";
        boolean isLucky = random.nextInt(1, 101) <= winRate;
        if (isLucky) {
            Product luckyProduct = getLuckyProduct();
            givenAwayProducts.add(luckyProduct.getProductType());
            System.out.println("Woww!! Congratulation you got another "+ luckyProduct.getProductType().getName() + " for free!!");
            if (getRemainLimit() == 0) {
                System.out.println("The promotion program is now ended.");
                machine.setDispenseStrategy(new NoPromoStrategy(machine));
            }
        }
        do {
            System.out.println("Got your stuff? (Y/N) ");
            gotStuff = scanner.next().toUpperCase(Locale.ROOT);
        } while (gotStuff.equals("N"));
    }

    public int getWinRate() {
        return winRate;
    }

    public void increaseWinRate() {
        this.winRate = (winRate * (100 + INCREASE_RATE))/100;
        if (this.winRate >= 100) this.winRate = 100;
    }

    public int getRemainLimit () {
        int cost = 0;
        for (ProductType product: givenAwayProducts) {
            cost += product.getPrice();
        }
        return PROMO_LIMIT - cost;
    }

}
