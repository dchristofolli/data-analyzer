package com.dchristofolli.dataanalyzer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SaleDataOutput {
    private final String fileName;
    private final int totalCustomers;
    private final int totalSalesmen;
    private final String mostExpensiveSale;
    private final String worstSalesman;

    public SaleDataOutput(String fileName,
                          int totalCustomers,
                          int totalSalesmen,
                          String mostExpensiveSale,
                          String worstSalesman) {
        this.fileName = fileName;
        this.totalCustomers = totalCustomers;
        this.totalSalesmen = totalSalesmen;
        this.mostExpensiveSale = mostExpensiveSale;
        this.worstSalesman = worstSalesman;
    }

    @Override
    public String toString() {
        return "Analysis Result:" +
            "\nCustomers amount: " + totalCustomers +
            "\nSalesmen amount: " + totalSalesmen +
            "\nMost expensive sale ID: '" + mostExpensiveSale + '\'' +
            "\nWorst salesman name: " + worstSalesman;
    }

    public String getFileName() {
        return fileName;
    }
}
