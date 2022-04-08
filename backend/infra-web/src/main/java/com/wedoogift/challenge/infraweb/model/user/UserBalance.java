package com.wedoogift.challenge.infraweb.model.user;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserBalance {
    private double meal;
    private double gift;

    public UserBalance(double meal, double gift) {
        this.gift = new BigDecimal(gift).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.meal = new BigDecimal(meal).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getGift() {
        return gift;
    }

    public void setGift(Float gift) {
        this.gift = gift;
    }

    public double getMeal() {
        return meal;
    }

    public void setMeal(Float meal) {
        this.meal = meal;
    }
}
