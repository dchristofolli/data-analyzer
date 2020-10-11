package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.dto.SaleDataInput;
import com.dchristofolli.dataanalyzer.dto.SaleDataOutput;
import com.dchristofolli.dataanalyzer.dto.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class Analyzer {
    private final FileReader fileReader;
    private final String homePath = System.getProperty("user.home");
    private final Logger logger = LoggerFactory.getLogger(Analyzer.class);

    public Analyzer(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @PostConstruct
    public SaleDataOutput analyze() {
        File inputPath = new File(String.valueOf(Path.of(
            homePath,
            "data",
            "in"
        )));
        List<SaleDataInput> files = fileReader.findFiles(inputPath);
        int customerCount = customerCounter(files);
        int salesmenCount = salesmenCounter(files);
        String expensiveSaleId = expensiveSaleVerifier(files);
        String worstSalesman = worstSalesmanVerifier(files);
        SaleDataOutput saleDataOutput = new SaleDataOutput(
            customerCount,
            salesmenCount,
            expensiveSaleId,
            worstSalesman
        );
        logger.info(saleDataOutput.toString());
        return saleDataOutput;
    }

    private String worstSalesmanVerifier(List<SaleDataInput> files) {
        AtomicReference<Double> minValue = new AtomicReference<>((double) 0);
        AtomicReference<String> salesmanName = new AtomicReference<>();
        files.stream()
            .filter(saleDataInput -> saleDataInput.getSale() != null)
            .forEach(saleDataInput -> {
                double total = saleDataInput.getSale().getTotal();
                if (total < minValue.get() || minValue.get().equals((double) 0)) {
                    minValue.set(total);
                    salesmanName.set(saleDataInput.getSale().getSalesmanName());
                }
            });
        return salesmanName.get();
    }

    private String expensiveSaleVerifier(List<SaleDataInput> files) {
        AtomicReference<Double> maxValue = new AtomicReference<>((double) 0);
        AtomicReference<String> id = new AtomicReference<>("");
        files.stream()
            .filter(saleDataInput -> saleDataInput.getSale() != null)
            .forEach(saleDataInput -> {
                double total = saleDataInput.getSale().getTotal();
                if (total > maxValue.get()) {
                    maxValue.set(total);
                    id.set(saleDataInput.getSale().getId());
                }
            });
        return id.get();
    }

    private int salesmenCounter(List<SaleDataInput> files) {
        return (int) files.stream()
            .filter(saleDataInput -> saleDataInput.getSalesman() != null)
            .count();
    }

    private int customerCounter(List<SaleDataInput> files) {
        return (int) files.stream()
            .filter(saleDataInput -> saleDataInput.getCustomer() != null)
            .count();
    }
}
