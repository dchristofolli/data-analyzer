package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.Stub;
import com.dchristofolli.dataanalyzer.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AnalyzerTest {
    @InjectMocks
    Analyzer analyzer;

    @Test
    void analyze() {

    }

    @Test
    void shouldRenameFile() {
        LineModel lineModel = Stub.saleDataInputStub().stream().findFirst().get();
        AtomicReference<String> fileName = new AtomicReference<>("rename.dat");
        File file1 = new File("rename.dat");
        String replace = file1.getName().replace(".dat", ".rd");
        analyzer.renameFile(fileName, lineModel);
        Assertions.assertEquals("rename.rd", replace);
    }

    @Test
    void worstSalesmanVerifier() {
        String salesman = Stub.salesmanStub().getName();
        List<LineModel> lineModels = Stub .saleDataInputStub();
        String salesmanName = analyzer.worstSalesmanVerifier(lineModels);
        Assertions.assertEquals(salesman, salesmanName);
    }

    @Test
    void expensiveSaleVerifier() {
        List<LineModel> lineModels = new ArrayList<>();
        List<Item> items = Collections.singletonList(new Item("1", 1, 1000.00));
        LineModel lineModel = LineModelBuilder.aSaleDataInput()
            .withSale(new Sale(
                "10",
                items,
                "Pedro",
                1000.00)).build();
        lineModels.add(lineModel);
        String expensiveSale = analyzer.expensiveSaleVerifier(lineModels);
        Assertions.assertEquals("10", expensiveSale);
    }

    @Test
    void salesmenCounter() {
        List<LineModel> lineModels = Stub .saleDataInputStub();
        int salesmenCount = analyzer.salesmenCounter(lineModels);
        Assertions.assertEquals(1, salesmenCount);
    }

    @Test
    void customerCounter() {
        List<LineModel> lineModels = Stub .saleDataInputStub();
        int customerCount = analyzer.customerCounter(lineModels);
        Assertions.assertEquals(1, customerCount);
    }

    @Test
    void getFileName() {
        List<LineModel> lineModels = Stub .saleDataInputStub();
        String fileName = analyzer.getFileName(lineModels);
        Assertions.assertEquals("test.done.dat", fileName);
    }
}