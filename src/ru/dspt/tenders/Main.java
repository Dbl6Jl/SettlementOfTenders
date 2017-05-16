package ru.dspt.tenders;


import ru.dspt.tenders.logic.OptimalRateCalculator;
import ru.dspt.tenders.logic.RateInputReader;
import ru.dspt.tenders.model.Order;

import java.util.List;

public class Main {
    public static final int TRADE_TIME_IN_MILLIS = 20_000;

    public static void main(String[] args) {
        RateInputReader reader = new RateInputReader();
        List<Order> orders = reader.readStandartInput();
        OptimalRateCalculator calculator = new OptimalRateCalculator();
        String result = calculator.calculateResult(orders);
        System.out.println("OUTPUT:");
        System.out.println(result);
    }
}
