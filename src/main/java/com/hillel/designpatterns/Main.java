package com.hillel.designpatterns;

import com.hillel.designpatterns.orderprocessor.order.Order;

public class Main {

    public static void main(String[] args) {

        Order order1 = ChainAndDecoratorPatternExamples.createOrder1Example();
        Order order2 = ChainAndDecoratorPatternExamples.createOrder2Example();

        System.out.println("======= task1 - chain of responsibility =======");
                ChainAndDecoratorPatternExamples.createChain(ChainAndDecoratorPatternExamples.createOrder1Example());
        ChainAndDecoratorPatternExamples.createChain(ChainAndDecoratorPatternExamples.createOrder2Example());

        System.out.println("============== task2 - decorator ==============");
        ChainAndDecoratorPatternExamples.createDecorator(order1);
        ChainAndDecoratorPatternExamples.createDecorator(order2);

        System.out.println("=============== task3 - facade ================");
        FacadePatternExample.createFacadeExample();

    }

}
