package com.dchristofolli.dataanalyzer.dto;

public class Item {
    private final String id;
    private final int quantity;
    private final double price;

    public Item(String id, int quantity, double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
            "id='" + id + '\'' +
            ", quantity=" + quantity +
            ", price=" + price +
            '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
