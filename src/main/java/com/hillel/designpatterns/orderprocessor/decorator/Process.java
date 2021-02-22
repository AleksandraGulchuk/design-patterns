package com.hillel.designpatterns.orderprocessor.decorator;

import com.hillel.designpatterns.orderprocessor.order.Order;

public interface Process {

    void processing(Order order);

}
