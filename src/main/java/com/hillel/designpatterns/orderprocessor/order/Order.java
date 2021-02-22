package com.hillel.designpatterns.orderprocessor.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class Order {

    private String id;
    private String contact;
    private Map<Pizza, Integer> pizzaMap;
    private BigDecimal orderSum;

}
