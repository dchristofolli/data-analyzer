package com.dchristofolli.dataanalyzer.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class FileReader {
    private final Logger logger = LoggerFactory.getLogger(FileReader.class);

    @PostConstruct
    public void run() {
        Stream<Path> filesInFolder = listFilesInFolder();
        readFiles(filesInFolder);
    }

    public Stream<Path> listFilesInFolder() {
        Stream<Path> walk = Stream.empty();
        try {
            walk = Files.walk(Paths.get(
                System.getProperty("user.home"),
                "data",
                "in"));
        } catch (IOException e) {
            logger.error("The directory could not be found. Check that the directory exists in the path {}",
                e.getMessage());
        }
        return walk;
    }

    public void readFiles(Stream<Path> filesInFolder) {
        filesInFolder.filter(Files::isRegularFile)
            .filter(file -> file.getFileName().toString().endsWith(".dat"))
            .forEach(path -> logger.info(path.getFileName().toString()));
    }
}
