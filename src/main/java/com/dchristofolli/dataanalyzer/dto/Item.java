package com.dchristofolli.dataanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String id;
    private int quantity;
    private double price;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
