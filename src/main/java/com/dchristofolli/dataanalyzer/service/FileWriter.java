package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.dto.SaleDataOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

@Component
public class FileWriter {
    private final String homePath = System.getProperty("user.home");
    private final Logger logger = LoggerFactory.getLogger(FileWriter.class);

    public void makeFile(SaleDataOutput data) {
        try {
            var fileOutputStream = new FileOutputStream(String.valueOf(Path.of(homePath,
                "data",
                "out",
                data.getFileName())));
            fileOutputStream.write(data.toString().getBytes());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}