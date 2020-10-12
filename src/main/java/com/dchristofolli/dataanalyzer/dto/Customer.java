package com.dchristofolli.dataanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private final String cnpj;
    private final String name;
    private final String businessAreaName;

    public Customer(String cnpj, String name, String businessAreaName) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessAreaName = businessAreaName;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "cnpj='" + cnpj + '\'' +
            ", name='" + name + '\'' +
            ", businessAreaName='" + businessAreaName + '\'' +
            '}';
    }

    public String getCnpj() {
        return cnpj;
    }
}
