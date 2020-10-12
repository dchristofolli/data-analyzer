package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.Stub;
import com.dchristofolli.dataanalyzer.dto.LineModel;
import com.dchristofolli.dataanalyzer.dto.Salesman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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
import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(SpringExtension.class)
class FileReaderTest {
    @Mock
    EntityMapper entityMapper;
    @InjectMocks
    FileReader fileReader;

    String homePath = "./test";
    List<LineModel> lineModelList = Stub.saleDataInputStub();
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
    void findFileShouldFindFileWhenOk() {
        fileReader.findFile(folder);
        Assertions.assertEquals(1, Arrays.stream(Objects.requireNonNull(folder.listFiles())).count());
    }

    @Test
    void shouldRenameFile() {
        File file1 = new File("rename.dat");
        String replace = file1.getName().replace(".dat", ".rd");
        Assertions.assertFalse(fileReader.renameFile(file1));
        Assertions.assertEquals("rename.rd", replace);
    }

    @Test
    void readFile() {
        LineModel lineModel = lineModelList.stream().findFirst().get();
        BDDMockito.when(entityMapper.mapToEntity("001ç1234567891234çPedroç50000"))
            .thenReturn(lineModel);
        List<LineModel> readFile = fileReader.readFile(file);
        Assertions.assertEquals(3, readFile.size());
        Assertions.assertEquals("Pedro", lineModel.getSalesman().getName());
    }

    @Test
    void readFileWhenSalesmanNameContainsCedilha() {
        LineModel lineModel = new LineModel(
            "test.dat",
            new Salesman("1234567891234",
                "Lourenço", 5000.0),
            null, null);
        BDDMockito.when(entityMapper.mapToEntity("001ç1234567891234çLourençoç50000"))
            .thenReturn(lineModel);
        fileReader.readFile(file);
        Assertions.assertEquals("Lourenço", lineModel.getSalesman().getName());
    }

    @Test
    void readFile_shouldThrowException_whenFileIsFolder() {
        fileReader.readFile(folder);
        Assertions.assertThrows(IOException.class, () -> Files.readAllLines(folder.toPath()));
    }

    @Test
    void getFileFromFolder() {
        AtomicReference<List<LineModel>> lineModels = new AtomicReference<>(Stub.saleDataInputStub());
        File file1 = new File("rename.dat");
        List<LineModel> lineModel = fileReader.readFile(file1);
        lineModels.set(lineModel);
        List<LineModel> fileFromFolder = fileReader.getFileFromFolder(file1);
        Assertions.assertEquals(lineModels.get(), fileFromFolder);
    }
}