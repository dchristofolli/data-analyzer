package com.dchristofolli.dataanalyzer.dto;

public class SaleDataOutput {
    private Integer totalCustomer;
    private Integer totalSalesMan;
    private Double mostExpensiveSaleValue;
    private Double cheapestSaleValue;
    private String mostExpensiveSale;
    private String worstSalesman;

    public SaleDataOutput(Integer totalCustomer, Integer totalSalesMan, Double mostExpensiveSaleValue,
                          Double cheapestSaleValue, String mostExpensiveSale, String worstSalesman) {
        this.totalCustomer = totalCustomer;
        this.totalSalesMan = totalSalesMan;
        this.mostExpensiveSaleValue = mostExpensiveSaleValue;
        this.cheapestSaleValue = cheapestSaleValue;
        this.mostExpensiveSale = mostExpensiveSale;
        this.worstSalesman = worstSalesman;
    }

    public SaleDataOutput() {
    }

    public Integer getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(Integer totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public Integer getTotalSalesMan() {
        return totalSalesMan;
    }

    public void setTotalSalesMan(Integer totalSalesMan) {
        this.totalSalesMan = totalSalesMan;
    }

    public Double getMostExpensiveSaleValue() {
        return mostExpensiveSaleValue;
    }

    public void setMostExpensiveSaleValue(Double mostExpensiveSaleValue) {
        this.mostExpensiveSaleValue = mostExpensiveSaleValue;
    }

    public Double getCheapestSaleValue() {
        return cheapestSaleValue;
    }

    public void setCheapestSaleValue(Double cheapestSaleValue) {
        this.cheapestSaleValue = cheapestSaleValue;
    }

    public String getMostExpensiveSale() {
        return mostExpensiveSale;
    }

    public void setMostExpensiveSale(String mostExpensiveSale) {
        this.mostExpensiveSale = mostExpensiveSale;
    }

    public String getWorstSalesman() {
        return worstSalesman;
    }

    public void setWorstSalesman(String worstSalesman) {
        this.worstSalesman = worstSalesman;
    }

    @Override
    public String toString() {
        return "SaleData{" +
            "totalCustomer=" + totalCustomer +
            ", totalSalesMan=" + totalSalesMan +
            ", mostExpensiveSaleValue=" + mostExpensiveSaleValue +
            ", cheapestSaleValue=" + cheapestSaleValue +
            ", mostExpensiveSale='" + mostExpensiveSale + '\'' +
            ", worstSalesman='" + worstSalesman + '\'' +
            '}';
    }
}
