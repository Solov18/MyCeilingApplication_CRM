package ru.gb.diplom.client;

public enum Industry {
    IT("Information Technology"),
    TELCO("Telecommunication"),
    MANUFACTURE("Manufacturing"),
    BANK("Banking Services"),
    CONSULTING("Consulting"),
    FINANCE("Finance"),
    GOVERNMENT("Government"),
    DELIVERY("Delivery"),
    ENTERTAINMENT("Entertainment"),
    NONPROFIT("Non-Profit"),
    OTHER("Other");


    private String name;

    Industry(String name) {
        this.name = name;
    }


}
