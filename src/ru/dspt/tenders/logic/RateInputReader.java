package ru.dspt.tenders.logic;

import ru.dspt.tenders.exception.IllegalBidFormatException;
import ru.dspt.tenders.model.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static ru.dspt.tenders.Main.TRADE_TIME_IN_MILLIS;

/**
 * Created by dsptushkin on 16.05.17.
 */
public class RateInputReader {
    public List<Order> readStandartInput() {
        System.out.println("INPUT:");
        long begin = System.currentTimeMillis();
        List<Order> orders = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            while ((System.currentTimeMillis() - begin) < TRADE_TIME_IN_MILLIS) {
                if (in.ready()) {
                    String nextBid = in.readLine();
                    try {
                        orders.add(OrderCreator.create(nextBid));
                    } catch (IllegalBidFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
