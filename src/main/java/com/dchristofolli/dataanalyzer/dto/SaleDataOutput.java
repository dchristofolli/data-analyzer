package com.dchristofolli.dataanalyzer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SaleDataOutput {
    private final int totalCustomers;
    private final int totalSalesmen;
    private final String mostExpensiveSale;
    private final String worstSalesman;

    public SaleDataOutput(int totalCustomers, int totalSalesmen, String mostExpensiveSale, String worstSalesman) {
        this.totalCustomers = totalCustomers;
        this.totalSalesmen = totalSalesmen;
        this.mostExpensiveSale = mostExpensiveSale;
        this.worstSalesman = worstSalesman;
    }

    @Override
    public String toString() {
        return "SaleDataOutput{" +
            "totalCustomers=" + totalCustomers +
            ", totalSalesmen=" + totalSalesmen +
            ", mostExpensiveSale='" + mostExpensiveSale + '\'' +
            ", worstSalesman=" + worstSalesman +
            '}';
    }
}
