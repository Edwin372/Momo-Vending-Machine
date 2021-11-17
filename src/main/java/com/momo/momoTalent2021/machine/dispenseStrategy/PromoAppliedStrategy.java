package main.java.com.momo.momoTalent2021.machine.dispenseStrategy;


import main.java.com.momo.momoTalent2021.machine.inventory.Product;

import java.util.ArrayList;
import java.util.List;

public class PromoAppliedStrategy implements DispenseStrategy {
    private final int promoLimit = 50000;
    private List<Product> giveAwayProducts;
    private int winRate;

    public PromoAppliedStrategy() {
        this.giveAwayProducts = new ArrayList<>();
        this.winRate = 10;
    }

    @Override
    public void execute() {
    }

    public int getWinRate() {
        return winRate;
    }

    public void setWinRate(int winRate) {
        this.winRate = winRate;
    }

    public int getRemainLimit () {
        int cost = 0;
        for (Product product: giveAwayProducts) {
            cost += product.getProductType().getPrice();
        }
        return promoLimit - cost;
    }





}
