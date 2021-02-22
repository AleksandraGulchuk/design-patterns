package com.hillel.designpatterns.orderprocessor.decorator;

import com.hillel.designpatterns.orderprocessor.order.Order;
import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RequiredArgsConstructor
public class SaveInFileProcess implements Process {

    private final Process process;
    private final File file = new File("orders.txt");

    @Override
    public void processing(Order order) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            file.createNewFile();
            bufferedWriter.write(order + "\n");
            process.processing(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



