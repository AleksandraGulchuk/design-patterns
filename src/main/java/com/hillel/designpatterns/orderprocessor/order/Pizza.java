package com.hillel.designpatterns.orderprocessor.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public enum Pizza {
    MEAT_EATERS("001", "Meat Eaters Pizza", new BigDecimal(220),
            "Pepperoni, ham, Italian sausage, ground beef & mozzarella cheese."),

    WORKS("002", "Works Pizza", new BigDecimal(230),
            "Pepperoni, ham, Italian sausage, ground beef, " +
                    "mushrooms, red onions, green peppers, black olives & extra mozzarella cheese."),

    HOWIE_MAUI("003", "Howie Maui Pizza", new BigDecimal(180),
            "Hungry Howie's Maui Pineapple Pizza with ham, bacon, pineapple & mozzarella cheese"),

    BBQ_CHICKEN("004", "BBQ Chicken Pizza", new BigDecimal(210),
            "Sweet BBQ sauce, grilled chicken breast, bacon, red onions & mozzarella cheese."),

    VEGGIE("005", "Veggie Pizza", new BigDecimal(150),
            "Mushrooms, red onions, green peppers, tomatoes, black olives & mozzarella cheese.");


    private final String id;
    private final String name;
    private final BigDecimal price;
    private final String description;
}
