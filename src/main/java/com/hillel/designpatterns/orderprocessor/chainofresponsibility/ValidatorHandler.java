package com.hillel.designpatterns.orderprocessor.chainofresponsibility;

import com.hillel.designpatterns.orderprocessor.order.Order;
import com.hillel.designpatterns.orderprocessor.order.Pizza;

import java.util.Map;

public class ValidatorHandler implements Handler {

    private Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Order order) {
        if (validateOrder(order)) {
            if (validateContact(order.getContact())) {
                if (validatePizzaMap(order.getPizzaMap())) {
                    if (next != null) {
                        next.handle(order);
                    }
                } else System.out.println("Invalid order: Pizza not selected!");
            } else System.out.println("Invalid order: Contact number is incorrect!");
        } else System.out.println("Invalid order: Order is empty!");
    }

    private boolean validateOrder(Order order) {
        return order != null;
    }

    private boolean validateContact(String contact) {
        return contact != null && contact.matches("\\+380\\d{9}");
    }

    private boolean validatePizzaMap(Map<Pizza, Integer> pizzaMap) {
        return pizzaMap != null && !pizzaMap.isEmpty();
    }


}
