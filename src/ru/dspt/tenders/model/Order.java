package ru.dspt.tenders.model;

import java.math.BigDecimal;

/**
 * Модель заявки
 * Created by dsptushkin on 15.05.17.
 */
public class Order implements Comparable<Order> {
    private Type type;
    private int amount;
    private BigDecimal rate;

    public Order() {

    }

    @Override
    public int compareTo(Order o) {
        return rate.compareTo(o.getRate());
    }

    public enum Type {
        BUY, SELL
    }

    public Order(Type type, int amount, BigDecimal rate) {
        this.type = type;
        this.amount = amount;
        this.rate = rate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
