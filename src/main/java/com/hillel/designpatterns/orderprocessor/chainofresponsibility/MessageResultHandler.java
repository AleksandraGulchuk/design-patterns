package com.hillel.designpatterns.orderprocessor.chainofresponsibility;

import com.hillel.designpatterns.orderprocessor.order.Order;
import com.hillel.designpatterns.orderprocessor.order.Pizza;

import java.util.Map;

public class MessageResultHandler implements Handler {

    private Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Order order) {
        System.out.println("Thank you! Your order: №" + order.getId() + "" + " processed.");
        printOrder(order);
        if (next != null) {
            next.handle(order);
        }
    }

    private void printOrder(Order order) {
        for (Map.Entry<Pizza, Integer> item : order.getPizzaMap().entrySet()) {
            System.out.println("Pizza: " + item.getKey().getName() + ", quantity: " + item.getValue());
        }
        System.out.println("Total sum: " + order.getOrderSum() + " UAH");
    }
}
