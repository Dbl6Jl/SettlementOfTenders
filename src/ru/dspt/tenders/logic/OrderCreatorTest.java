package ru.dspt.tenders.logic;

import ru.dspt.tenders.model.Order;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by dsptushkin on 16.05.17.
 */
public class OrderCreatorTest {
    @org.junit.Test
    public void create() throws Exception {
        Order order = OrderCreator.create("B 100 100.50");
        assertEquals(Order.Type.BUY, order.getType());
        assertEquals(100, order.getAmount());
        assertEquals(new BigDecimal("100.50"), order.getRate());
    }

}