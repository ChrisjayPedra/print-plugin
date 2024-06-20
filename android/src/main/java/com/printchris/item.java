package com.printchris;

public class item {
    private String name;
    private String description;
    private String price;
    private String quantity;

    public item(String name, String description, String price,String quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
    public String getQuantity() {
        return quantity;
    }
}
