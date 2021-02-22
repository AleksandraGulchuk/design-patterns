package com.hillel.designpatterns;

import com.hillel.designpatterns.orderprocessor.chainofresponsibility.*;
import com.hillel.designpatterns.orderprocessor.decorator.InstallerParamProcess;
import com.hillel.designpatterns.orderprocessor.decorator.MessageResultProcess;
import com.hillel.designpatterns.orderprocessor.decorator.SaveInFileProcess;
import com.hillel.designpatterns.orderprocessor.decorator.ValidatorProcess;
import com.hillel.designpatterns.orderprocessor.order.Order;
import com.hillel.designpatterns.orderprocessor.order.Pizza;

import java.util.EnumMap;
import java.util.Map;


public class ChainAndDecoratorPatternExamples {

    private ChainAndDecoratorPatternExamples() {
    }

    public static void createChain(Order order) {
        Handler validatorHandler = new ValidatorHandler();
        Handler installerHandel = new InstallerParamHandler();
        Handler saveHandler = new SaveInFileHandler();
        Handler messageHandler = new MessageResultHandler();

        validatorHandler.setNext(installerHandel);
        installerHandel.setNext(saveHandler);
        saveHandler.setNext(messageHandler);

        validatorHandler.handle(order);
    }

    public static void createDecorator(Order order) {
        ValidatorProcess process = new ValidatorProcess(
                new InstallerParamProcess(
                        new SaveInFileProcess(
                                new MessageResultProcess())));

        process.processing(order);
    }

    public static Order createOrder1Example() {
        Order order1 = new Order();
        Map<Pizza, Integer> pizzaMap = new EnumMap(Pizza.class);
        pizzaMap.put(Pizza.MEAT_EATERS, 1);
        pizzaMap.put(Pizza.BBQ_CHICKEN, 2);
        pizzaMap.put(Pizza.WORKS, 1);

        order1.setContact("+380987654321");
        order1.setPizzaMap(pizzaMap);

        return order1;
    }

    public static Order createOrder2Example() {
        Order order2 = new Order();
        Map<Pizza, Integer> pizzaMap2 = new EnumMap(Pizza.class);
        pizzaMap2.put(Pizza.VEGGIE, 3);
        pizzaMap2.put(Pizza.HOWIE_MAUI, 2);

        order2.setContact("+3809876543");
        order2.setPizzaMap(pizzaMap2);

        return order2;
    }
}
