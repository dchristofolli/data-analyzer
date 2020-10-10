package com.dchristofolli.dataanalyzer.service;

import org.springframework.stereotype.Service;

@Service
public class DataAnalyzer {
    private final FileReader fileReader;

    public DataAnalyzer(FileReader fileReader) {
        this.fileReader = fileReader;
    }


}
