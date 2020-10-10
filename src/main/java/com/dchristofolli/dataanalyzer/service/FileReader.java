package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.dto.SaleDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FileReader {
    private final Logger logger = LoggerFactory.getLogger(FileReader.class);
    private final String homePath = System.getProperty("user.home");
    private final EntityMapper entityMapper;

    public FileReader(EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

    @PostConstruct
    public void run() {
        File inputPath = new File(String.valueOf(Path.of(
            homePath,
            "data",
            "in"
        )));
        findFiles(inputPath);
    }

    public void findFiles(File structure) {
        for (File file : Objects.requireNonNull(structure.listFiles())) {
            if (file.isDirectory())
                findFiles(file);
            else {
                if (file.getName().endsWith(".dat")) {
                    readFile(file);
                }
            }
        }
    }

    public List<SaleDataInput> readFile(File file) {
        List<SaleDataInput> saleDataInputs = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            allLines.parallelStream()
                .forEach(line -> saleDataInputs.add(entityMapper.mapToEntity(line)));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        saleDataInputs.forEach(saleDataInput -> logger.info(saleDataInput.toString()));
        return saleDataInputs;
    }
}
