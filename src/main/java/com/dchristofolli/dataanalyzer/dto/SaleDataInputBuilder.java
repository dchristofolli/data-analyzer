package com.dchristofolli.dataanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class SaleDataInputBuilder {
    private Salesman salesman;
    private Customer customer;
    private Sale sale;

    private SaleDataInputBuilder() {
    }

    public static SaleDataInputBuilder aSaleDataInput() {
        return new SaleDataInputBuilder();
    }

    public SaleDataInputBuilder withSalesman(Salesman salesman) {
        this.salesman = salesman;
        return this;
    }

    public SaleDataInputBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public SaleDataInputBuilder withSale(Sale sale) {
        this.sale = sale;
        return this;
    }

    public SaleDataInput build() {
        SaleDataInput saleDataInput = new SaleDataInput();
        saleDataInput.setSalesman(salesman);
        saleDataInput.setCustomer(customer);
        saleDataInput.setSale(sale);
        return saleDataInput;
    }
}
