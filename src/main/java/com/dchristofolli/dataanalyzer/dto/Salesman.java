package com.dchristofolli.dataanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Salesman {
    private String cpf;
    private String name;
    private double salary;

    public Salesman(String cpf, String name, double salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public Salesman() {
    }

    @Override
    public String toString() {
        return "Salesman{" +
            "cpf='" + cpf + '\'' +
            ", name='" + name + '\'' +
            ", salary=" + salary +
            '}';
    }

    public String getName() {
        return name;
    }
}
