package com.dchristofolli.dataanalyzer.dto;

import java.util.List;

public class Sale {
    private String id;
    private List<Item> items;
    private String salesmanName;
    private double total;

    public Sale(String id, List<Item> items, String salesmanName, double total) {
        this.id = id;
        this.items = items;
        this.salesmanName = salesmanName;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Sale{" +
            "id='" + id + '\'' +
            ", items=" + items +
            ", salesmanName='" + salesmanName + '\'' +
            ", total=" + total +
            '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
