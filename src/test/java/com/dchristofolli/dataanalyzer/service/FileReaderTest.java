package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.Stub;
import com.dchristofolli.dataanalyzer.dto.LineModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Profile("test")
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
    void findFile_shouldFindFile_whenOk() {
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
    }

//    @Test
//    void readFile_shouldThrowException_whenFileIsFolder() throws IOException {
//        fileReader.readFile(folder);
//        Files.readAllLines(folder.toPath());
//        Assertions.assertThrows(IOException.class, () -> Files.readAllLines(folder.toPath()));
//    }
}