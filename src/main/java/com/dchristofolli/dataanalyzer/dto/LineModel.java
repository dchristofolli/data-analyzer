package com.dchristofolli.dataanalyzer.dto;

public class LineModel {
    private String fileName;
    private Salesman salesman;
    private Customer customer;
    private Sale sale;

    public LineModel() {
    }

    public LineModel(String fileName, Salesman salesman, Customer customer, Sale sale) {
        this.fileName = fileName;
        this.salesman = salesman;
        this.customer = customer;
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "LineModel{" +
            "fileName='" + fileName + '\'' +
            ", salesman=" + salesman +
            ", customer=" + customer +
            ", sale=" + sale +
            '}';
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
