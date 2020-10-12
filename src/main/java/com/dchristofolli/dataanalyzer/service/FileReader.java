package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.dto.LineModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class FileReader {
    private final Logger logger = LoggerFactory.getLogger(FileReader.class);
    private final EntityMapper entityMapper;
    private final String homePath = System.getProperty("user.home");

    public FileReader(EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

    public List<LineModel> findFile(File structure) {
        AtomicReference<List<LineModel>> dataInputList = new AtomicReference<>(Collections.emptyList());
        Arrays.stream(Objects.requireNonNull(structure.listFiles()))
            .filter(file -> file.getName().endsWith(".dat"))
            .findFirst().ifPresent(file -> {
            dataInputList.set(readFile(file));
            renameFile(file);
        });
        return dataInputList.get();
    }

    public boolean renameFile(File file) {
        File newFile = new File(String.valueOf(Path.of(
            homePath,
            "data",
            "in",
            file.getName().replace(".dat", ".rd")
        )));
        return file.renameTo(newFile);
    }

    public List<LineModel> readFile(File file) {
        List<LineModel> lineModels = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            allLines.parallelStream()
                .forEach(line -> lineModels.add(entityMapper.mapToEntity(line)));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        setFileName(file, lineModels);
        return lineModels;
    }

    public void setFileName(File file, List<LineModel> lineModels) {
        lineModels.stream()
            .filter(Objects::nonNull)
            .forEach(saleDataInput -> saleDataInput.setFileName(file.getName()));
    }
}
