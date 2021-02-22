package com.hillel.designpatterns.orderprocessor.decorator;

import com.hillel.designpatterns.orderprocessor.order.Order;
import com.hillel.designpatterns.orderprocessor.order.Pizza;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
public class InstallerParamProcess implements Process {

    private final Process process;
    private final File file = new File("indexMemory.txt");


    @Override
    public void processing(Order order) {
        order.setId(createID());
        order.setOrderSum(countTotalSum(order.getPizzaMap()));
        process.processing(order);
    }


    private BigDecimal countTotalSum(Map<Pizza, Integer> pizzaMap) {
        BigDecimal totalSum = new BigDecimal(0);
        for (Map.Entry<Pizza, Integer> entry : pizzaMap.entrySet()) {
            totalSum = totalSum.add(
                    entry.getKey().getPrice()
                            .multiply(new BigDecimal(entry.getValue())));
        }
        return totalSum;
    }

    private String createID() {
        int countId;
        try {
            if (file.createNewFile()) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getName()))) {
                    bufferedWriter.write(1 + "\n");
                }
            }
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                countId = Integer.parseInt(bufferedReader.readLine());
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getName()))) {
                bufferedWriter.write((countId + 1) + "\n");
                String format = String.format("%%0%dd", 7);
                return String.format(format, countId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Id order not created!");
    }

}
