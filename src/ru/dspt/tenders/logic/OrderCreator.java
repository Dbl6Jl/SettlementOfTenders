package ru.dspt.tenders.logic;

import ru.dspt.tenders.exception.IllegalBidFormatException;
import ru.dspt.tenders.model.Order;

import java.math.BigDecimal;


/**
 * Created by dsptushkin on 15.05.17.
 */
public class OrderCreator {
    public static Order create(String s) throws IllegalBidFormatException {
        Order order = new Order();

        String[] splittedInpit = s.split(" ");
        checkInput(splittedInpit);
        order.setType(splittedInpit[0].equals("B") ? Order.Type.BUY : Order.Type.SELL);
        order.setAmount(Integer.parseInt(splittedInpit[1]));
        order.setRate(new BigDecimal(splittedInpit[2]));
        return order;
    }

    /**
     * @param splittedString
     */
    private static void checkInput(String[] splittedString) throws IllegalBidFormatException {
        if(splittedString.length != 3) {
            throw new IllegalBidFormatException("Заявка должна состоять из трех аргументов");
        }
        if(!splittedString[0].matches("B|S")) {
            throw new IllegalBidFormatException("Некорректный тип заявки");
        }
        try {
            int amount = Integer.parseInt(splittedString[1]);
            if(amount < 1 || amount > 1000) {
                throw new IllegalBidFormatException("Количество бумаг в заявке должно быть от 1 до 1000");
            }
        } catch (NumberFormatException e) {
            throw new IllegalBidFormatException("Неверный формат количества бумаг");
        }
        if(!splittedString[2].matches("1?\\d{0,2}\\.\\d{2}")) {
            throw new IllegalBidFormatException("Неверный формат цены бумаг");
        }
    }
}
