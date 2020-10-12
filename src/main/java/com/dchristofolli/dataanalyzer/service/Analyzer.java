package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.dto.LineModel;
import com.dchristofolli.dataanalyzer.dto.SaleDataOutput;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
@EnableScheduling
public class Analyzer {
    private final FileReader fileReader;
    private final FileWriter fileWriter;
    private final String homePath = System.getProperty("user.home");

    public Analyzer(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    @Scheduled(fixedDelay = 1000)
    public void analyze() {
        File inputPath = new File(String.valueOf(Path.of(
                homePath,
                "data",
                "in"
        )));
        List<LineModel> files = fileReader.findFile(inputPath);
        SaleDataOutput saleDataOutput = new SaleDataOutput(
                getFileName(files),
                customerCounter(files),
                salesmenCounter(files),
                expensiveSaleVerifier(files),
                worstSalesmanVerifier(files)
        );
        fileWriter.makeFile(saleDataOutput);
    }

    private String getFileName(List<LineModel> files) {
        AtomicReference<String> fileName = new AtomicReference<>("");
        files.stream().findFirst()
                .ifPresent(saleDataInput -> {
                    String file = saleDataInput.getFileName().replace("dat", "done.dat");
                    fileName.set(file);
                });
        return fileName.get();
    }

    private String worstSalesmanVerifier(List<LineModel> files) {
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

    private String expensiveSaleVerifier(List<LineModel> files) {
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

    private int salesmenCounter(List<LineModel> files) {
        return (int) files.stream()
                .filter(saleDataInput -> saleDataInput.getSalesman() != null)
                .count();
    }

    private int customerCounter(List<LineModel> files) {
        return (int) files.stream()
                .filter(saleDataInput -> saleDataInput.getCustomer() != null)
                .count();
    }
}
