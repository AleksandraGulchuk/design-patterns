package com.hillel.designpatterns.orderprocessor.decorator;

import com.hillel.designpatterns.orderprocessor.order.Order;
import com.hillel.designpatterns.orderprocessor.order.Pizza;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ValidatorProcess implements Process {

    private final Process process;

    @Override
    public void processing(Order order) {
        if (validateOrder(order)) {
            if (validateContact(order.getContact())) {
                if (validatePizzaMap(order.getPizzaMap())) {
                    process.processing(order);
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
