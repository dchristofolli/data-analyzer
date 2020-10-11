package com.dchristofolli.dataanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sale {
    private final String id;
    private final List<Item> items;
    private final String salesmanName;
    private final double total;

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

    public String getSalesmanName() {
        return salesmanName;
    }

    public double getTotal() {
        return total;
    }
}
