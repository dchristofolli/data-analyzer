package com.dchristofolli.dataanalyzer;

import com.dchristofolli.dataanalyzer.dto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stub {
    public static List<LineModel> saleDataInputStub() {
        List<LineModel> dataInputList = new ArrayList<>();
        LineModel dataInput = new LineModel(
            "test.dat",
            salesmanStub(),
            customerStub(),
            saleStub()
        );
        dataInputList.add(dataInput);
        return dataInputList;
    }

    public static Sale saleStub() {
        List<Item> itemList = Arrays.asList(
            new Item("1", 10, 100.0),
            new Item("2", 30, 2.5),
            new Item("3", 40, 3.1));
        return new Sale(
            "10",
            itemList,
            "Pedro",
            0
        );
    }

    public static Customer customerStub() {
        return new Customer(
            "2345675434544345",
            "Jose da Silva",
            "Rural"
        );
    }

    public static Salesman salesmanStub() {
        return new Salesman("1234567891234", "Pedro", 50000.0);
    }

    public static SaleDataOutput saleDataOutputStub() {
        return new SaleDataOutput(
            "test.done.dat",
            1,
            1,
            "10",
            "Pedro");
    }
}
