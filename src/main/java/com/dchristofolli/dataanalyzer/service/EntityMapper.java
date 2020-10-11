package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EntityMapper {
    private final Logger logger = LoggerFactory.getLogger(EntityMapper.class);

    public SaleDataInput mapToEntity(String line) {
        SaleDataInput saleDataInput = new SaleDataInput();
        String type = dataTypeChecker(line);
        if (type.equals("001")) {
            saleDataInput = SaleDataInputBuilder
                .aSaleDataInput()
                .withSalesman(createSalesman(line))
                .build();
        }
        if (type.equals("002")) {
            saleDataInput = SaleDataInputBuilder
                .aSaleDataInput()
                .withCustomer(createCustomer(line))
                .build();
        }
        if (type.equals("003")) {
            saleDataInput = SaleDataInputBuilder
                .aSaleDataInput()
                .withSale(createSale(line))
                .build();
        }
        return saleDataInput;
    }

    public Customer createCustomer(String line) {
        int lastIndexOfSeparator = line.lastIndexOf('ç');
        String cnpj = line.substring(4, 20);
        String name = line.substring(21, lastIndexOfSeparator);
        String businessArea = line.substring(lastIndexOfSeparator + 1);
        return new Customer(cnpj, name, businessArea);
    }

    public Salesman createSalesman(String line) {
        int lastIndexOfSeparator = line.lastIndexOf('ç');
        String cpf = line.substring(4, 17);
        String name = line.substring(18, lastIndexOfSeparator);
        double salary = Double.parseDouble(line.substring(lastIndexOfSeparator + 1));
        return new Salesman(cpf, name, salary);
    }

    public String dataTypeChecker(String data) {
        return data.substring(0, 3);
    }

    public Sale createSale(String line) {
        List<Item> itemList = new ArrayList<>();
        String saleId = line.substring(line.indexOf('ç') + 1, line.indexOf('[') - 1);
        String salesman = line.substring(line.lastIndexOf('ç') +1 );
        String value = line.substring(line.indexOf('[') + 1, line.lastIndexOf(']'));
        String[] items = value.split(",");
        Arrays.stream(items)
            .forEach(s -> {
                String id = s.substring(0, s.indexOf('-'));
                int quantity = Integer.parseInt(s.substring(s.indexOf('-') + 1, s.lastIndexOf('-')));
                double price = Double.parseDouble(s.substring(s.lastIndexOf('-') + 1));
                itemList.add(new Item(id, quantity, price));
            });
        double total = itemList.stream()
            .mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
        Sale sale = new Sale(saleId, itemList, salesman, total);
        logger.info(sale.toString());
        return sale;
    }
}
