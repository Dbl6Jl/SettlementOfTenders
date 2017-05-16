package ru.dspt.tenders.logic;

import ru.dspt.tenders.model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Расчетник
 * Не статический, потому что методы расчета могут переопределиться/наследоваться итд.
 * Created by dsptushkin on 16.05.17.
 */
public class OptimalRateCalculator {
    public String calculateResult(List<Order> orders) {
        int totalAmount = 0;
        BigDecimal rate = BigDecimal.ZERO;
        List<Order> sellOrders = getSellOrders(orders);
        List<Order> buyOrders = getBuyOrders(orders);
        // Если максимальная закупочная цена меньше минимальной цены продажи, аукцион не состоялся
        if((sellOrders.size() < 1 && buyOrders.size() < 1 ) || sellOrders.get(0).compareTo(buyOrders.get(0)) == 1) {
            return "0 n/a";
        }
        int i = 0;
        int j = 0;
        while( (sellOrders.size() > i) && (buyOrders.size() > j) && sellOrders.get(i).compareTo(buyOrders.get(j)) < 1) {
            if (sellOrders.get(i).getAmount() > buyOrders.get(j).getAmount()) {
                totalAmount += buyOrders.get(j).getAmount();
                sellOrders.get(i).setAmount(sellOrders.get(i).getAmount() - buyOrders.get(j).getAmount());
                j++;
                rate = sellOrders.get(i).getRate();
            } else if(sellOrders.get(i).getAmount() == buyOrders.get(j).getAmount()) {
                totalAmount += buyOrders.get(j).getAmount();
                rate = sellOrders.get(i).getRate().add(buyOrders.get(j).getRate()).divide(new BigDecimal(2), 2, BigDecimal.ROUND_UP);
                i++;
                j++;
            } else {
                totalAmount += sellOrders.get(i).getAmount();
                buyOrders.get(j).setAmount(buyOrders.get(j).getAmount() - sellOrders.get(i).getAmount());
                i++;
                rate = buyOrders.get(j).getRate();
            }
        }
        return String.valueOf(totalAmount) + " " + rate;
    }

    /**
     * @param orders
     * @return Заявки на закупку, отсортированные по убыванию
     */
    public List<Order> getBuyOrders(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getType().equals(Order.Type.BUY))
                .sorted((Order o1, Order o2) -> -o1.compareTo(o2))
                .collect(Collectors.toList());
    }

    /**
     * @param orders
     * @return Отсортированные заявки на продажу
     */
    public List<Order> getSellOrders(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getType().equals(Order.Type.SELL))
                .sorted()
                .collect(Collectors.toList());
    }
}
