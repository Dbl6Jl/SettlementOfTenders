package ru.dspt.tenders.logic;

import org.junit.Test;
import ru.dspt.tenders.model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dsptushkin on 16.05.17.
 */
public class OptimalRateCalculatorTest {
    public static List<Order> testBids() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Order.Type.BUY,  100,  new BigDecimal(15.36).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        orders.add(new Order(Order.Type.BUY,   50,  new BigDecimal(15.40).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        orders.add(new Order(Order.Type.SELL,  30,  new BigDecimal(15.30).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        orders.add(new Order(Order.Type.SELL, 100,  new BigDecimal(15.34).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        return orders;
    }
    public OptimalRateCalculator calculator = new OptimalRateCalculator();

    @Test
    public void calculateResult() throws Exception {
        assertEquals("130 15.36", calculator.calculateResult(testBids()));
    }

    @Test
    public void getBuyOrders() throws Exception {
        List<Order> buyOrders = calculator.getBuyOrders(testBids());
        assertEquals(2, buyOrders.size());

    }
}