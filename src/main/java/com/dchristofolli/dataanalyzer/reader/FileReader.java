package com.dchristofolli.dataanalyzer.reader;

import com.dchristofolli.dataanalyzer.dto.Customer;
import com.dchristofolli.dataanalyzer.dto.Item;
import com.dchristofolli.dataanalyzer.dto.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@EnableScheduling
public class FileReader {
    private final Logger logger = LoggerFactory.getLogger(FileReader.class);
    private final String homePath = System.getProperty("user.home");

    //    @Scheduled(fixedDelay = 1000)
    @PostConstruct
    public void run() {
        File file = new File(String.valueOf(Path.of(
            homePath,
            "data",
            "in"
        )));
        findFilesInFolderAndSubFolders(file);
    }

    private void findFilesInFolderAndSubFolders(File structure) {
        for (File file : Objects.requireNonNull(structure.listFiles())) {
            if (file.isDirectory())
                findFilesInFolderAndSubFolders(file);
            else {
                if (file.getName().endsWith(".dat"))
                    readFile(file);
            }
        }
    }

    private void readFile(File file) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            allLines.parallelStream()
                .forEach(this::mapToObject);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void mapToObject(String line) {
        String type = dataTypeChecker(line);
        List<Object> list = new ArrayList<>();
        if (type.equals("001")) {
            list.add(createSalesman(line));
        }
        if (type.equals("002")) {
            list.add(createCustomer(line));
        }
        if (type.equals("003")) {
            list.add(createItems(line));
        }
        list.forEach(o -> logger.info(o.toString()));
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
