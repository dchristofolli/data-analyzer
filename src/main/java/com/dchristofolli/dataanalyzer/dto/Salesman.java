package com.dchristofolli.dataanalyzer.dto;

public class Salesman {
    private final String cpf;
    private final String name;
    private final double salary;

    public Salesman(String cpf, String name, double salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
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
