package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.dto.SaleDataOutput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

@Component
public class FileWriter {
    @Value("${path.home}")
    private String homePath;

    public void makeFile(SaleDataOutput data) {
        try {
            var fileOutputStream = new FileOutputStream(String.valueOf(Path.of(homePath,
                "data",
                "out",
                data.getFileName())));
            fileOutputStream.write(data.toString().getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.getSuppressed();
        }
    }
}
