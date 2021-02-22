package com.hillel.designpatterns.orderprocessor.decorator;

import com.hillel.designpatterns.orderprocessor.order.Order;
import com.hillel.designpatterns.orderprocessor.order.Pizza;

import java.util.Map;

public class MessageResultProcess implements Process {

    @Override
    public void processing(Order order) {
        System.out.println("Thank you! Your order: №" + order.getId() + "" + " processed.");
        printOrder(order);
    }

    private void printOrder(Order order) {
        for (Map.Entry<Pizza, Integer> item : order.getPizzaMap().entrySet()) {
            System.out.println("Pizza: " + item.getKey().getName() + ", quantity: " + item.getValue());
        }
        System.out.println("Total sum: " + order.getOrderSum() + " UAH");
    }
}
