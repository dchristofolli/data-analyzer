package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.dto.Customer;
import com.dchristofolli.dataanalyzer.dto.Item;
import com.dchristofolli.dataanalyzer.dto.Salesman;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EntityMapper {
    public Object mapToEntity(String line) {
        String type = dataTypeChecker(line);
        Object object = new Object();
        if (type.equals("001")) {
            object = createSalesman(line);
        }
        if (type.equals("002")) {
            object = createCustomer(line);
        }
        if (type.equals("003")) {
            object = createItems(line);
        }
        return object;
    }

    private Customer createCustomer(String line) {
        int lastIndexOfSeparator = line.lastIndexOf('ç');
        String cnpj = line.substring(4, 20);
        String name = line.substring(21, lastIndexOfSeparator);
        String businessArea = line.substring(lastIndexOfSeparator + 1);
        return new Customer(cnpj, name, businessArea);
    }

    private Salesman createSalesman(String line) {
        int lastIndexOfSeparator = line.lastIndexOf('ç');
        String cpf = line.substring(4, 17);
        String name = line.substring(18, lastIndexOfSeparator);
        double salary = Double.parseDouble(line.substring(lastIndexOfSeparator + 1));
        return new Salesman(cpf, name, salary);
    }

    private String dataTypeChecker(String data) {
        return data.substring(0, 3);
    }

    private List<Item> createItems(String line) {
        List<Item> itemList = new ArrayList<>();
        String value = line.substring(line.indexOf('[') + 1, line.lastIndexOf(']'));
        String[] items = value.split(",");
        Arrays.stream(items)
            .forEach(s -> {
                String id = s.substring(0, s.indexOf('-'));
                int quantity = Integer.parseInt(s.substring(s.indexOf('-') + 1, s.lastIndexOf('-')));
                double price = Double.parseDouble(s.substring(s.lastIndexOf('-') + 1));
                itemList.add(new Item(id, quantity, price));
            });
        return itemList;
    }
}
