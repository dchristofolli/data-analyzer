package com.dchristofolli.dataanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private String cnpj;
    private String name;
    private String businessAreaName;

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

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessAreaName() {
        return businessAreaName;
    }

    public void setBusinessAreaName(String businessAreaName) {
        this.businessAreaName = businessAreaName;
    }
}
