package com.dchristofolli.dataanalyzer.service;

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
import java.util.List;
import java.util.Objects;

@Component
@EnableScheduling
public class FileReader {
    private final Logger logger = LoggerFactory.getLogger(FileReader.class);
    private final String homePath = System.getProperty("user.home");
    private final EntityMapper entityMapper;

    public FileReader(EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

    //    @Scheduled(fixedDelay = 1000)
    @PostConstruct
    public void run() {
        File inputPath = new File(String.valueOf(Path.of(
            homePath,
            "data",
            "in"
        )));
        findFiles(inputPath);
    }

    public List<Object> findFiles(File structure) {
        List<Object> objects = new ArrayList<>();
        for (File file : Objects.requireNonNull(structure.listFiles())) {
            if (file.isDirectory())
                findFiles(file);
            else {
                if (file.getName().endsWith(".dat")) {
                    objects.add(readFile(file));
                }
            }
        }
        return objects;
    }

    private List<Object> readFile(File file) {
        List<Object> list = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            allLines.parallelStream()
                .forEach(s -> list.add(entityMapper.mapToEntity(s)));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("{}\n{} read lines", list.toString(), list.size());
        return list;
    }
}
