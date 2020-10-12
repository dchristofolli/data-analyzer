package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.Stub;
import com.dchristofolli.dataanalyzer.dto.LineModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
class FileReaderTest {
    @Mock
    EntityMapper entityMapper;
    @InjectMocks
    FileReader fileReader;

    String homePath = "./test";
    List<LineModel> lineModel = Stub.saleDataInputStub();
    File folder;
    File file;
    File line;
    File[] files;

    @BeforeEach
    void init() {
        folder = new File(homePath);
        file = new File(String.valueOf(Path.of(homePath, "data", "in", "test.dat")));
        files = folder.listFiles();
        line = Arrays.stream(files != null ? files : new File[0]).findFirst().orElse(folder);
    }


    @Test
    void findFile_shouldFindFile_whenOk() {
        fileReader.findFile(folder);
        Assertions.assertEquals(1, Arrays.stream(Objects.requireNonNull(folder.listFiles())).count());
    }

    @Test
    void shouldRenameFile() {
        String replace = file.getName().replace(".dat", ".rd");
        Assertions.assertFalse(fileReader.renameFile(file));
        Assertions.assertEquals("test.rd", replace);
    }

    @Test
    void readFile() {
        List<LineModel> readFile = fileReader.readFile(file);
        Assertions.assertEquals(0, readFile.size());
    }

    @Test
    void readFile_shouldThrowException_whenFileIsFolder() throws IOException {
        File structure = new File(homePath);
        File[] files = structure.listFiles();
        assert files != null;
        File file = Arrays.stream(files).findFirst().orElse(structure);
        fileReader.readFile(structure);
        Files.readAllLines(file.toPath());
        Assertions.assertThrows(IOException.class, () -> Files.readAllLines(structure.toPath()));
    }
}