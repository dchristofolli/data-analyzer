package com.dchristofolli.dataanalyzer.dto;

public final class LineModelBuilder {
    private Salesman salesman;
    private Customer customer;
    private Sale sale;

    private LineModelBuilder() {
    }

    public static LineModelBuilder aSaleDataInput() {
        return new LineModelBuilder();
    }

    public LineModelBuilder withSalesman(Salesman salesman) {
        this.salesman = salesman;
        return this;
    }

    public LineModelBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public LineModelBuilder withSale(Sale sale) {
        this.sale = sale;
        return this;
    }

    public LineModel build() {
        LineModel lineModel = new LineModel();
        lineModel.setSalesman(salesman);
        lineModel.setCustomer(customer);
        lineModel.setSale(sale);
        return lineModel;
    }
}
