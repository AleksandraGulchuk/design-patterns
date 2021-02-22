package com.hillel.designpatterns.orderprocessor.chainofresponsibility;

import com.hillel.designpatterns.orderprocessor.order.Order;

import java.io.*;

public class SaveInFileHandler implements Handler {

    private Handler next;
    private final File file = new File("orders.txt");

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Order order) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            file.createNewFile();
            bufferedWriter.write(order + "\n");
            if (next != null) {
                next.handle(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
