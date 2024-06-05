package ru.gb.diplom.position;

import java.math.BigDecimal;

public enum Position {

    REGULAR("REGULAR", new BigDecimal("10000.00")),
    MANAGER("MANAGER", new BigDecimal("100000.00")),
    OWNER("OWNER", new BigDecimal("9999999999.99"));

    private String name;

    private BigDecimal limit;

    Position(String name, BigDecimal limit) {
        this.name = name;
        this.limit = limit;
    }

}
