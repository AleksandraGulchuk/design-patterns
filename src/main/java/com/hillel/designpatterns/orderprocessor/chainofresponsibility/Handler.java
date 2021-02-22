package com.hillel.designpatterns.orderprocessor.chainofresponsibility;

import com.hillel.designpatterns.orderprocessor.order.Order;

public interface Handler {

    void setNext(Handler next);

    void handle(Order order);

}
