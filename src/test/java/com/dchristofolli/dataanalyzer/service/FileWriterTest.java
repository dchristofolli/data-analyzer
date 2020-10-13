package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.Stub;
import com.dchristofolli.dataanalyzer.dto.SaleDataOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@ExtendWith(SpringExtension.class)
class FileWriterTest {
    String homePath = "./test";

    @InjectMocks
    FileWriter fileWriter;

    @Test
    void shouldMakeFileWhenSuccessfully() throws IOException {
        SaleDataOutput data = Stub.saleDataOutputStub();
        var fileOutputStream = new FileOutputStream(String.valueOf(Path.of(homePath,
            "data",
            "out",
            data.getFileName())));
        fileOutputStream.write(data.toString().getBytes());
        fileWriter.makeFile(data);
        boolean exists = Files.exists(Path.of(homePath, "data", "out", "test.done.dat"));
        Assertions.assertTrue(exists);
    }

    @Test
    void shouldThrowExceptionWhenNotSuccessfully() {
        SaleDataOutput data = Stub.saleDataOutputStub();
        fileWriter.makeFile(data);
        Assertions.assertThrows(
            IOException.class, () -> new FileOutputStream(homePath));
    }
}