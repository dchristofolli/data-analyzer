package com.dchristofolli.dataanalyzer.service;

import com.dchristofolli.dataanalyzer.dto.Customer;
import com.dchristofolli.dataanalyzer.dto.LineModel;
import com.dchristofolli.dataanalyzer.dto.Sale;
import com.dchristofolli.dataanalyzer.dto.Salesman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class EntityMapperTest {
    @InjectMocks
    EntityMapper entityMapper;

    @Test
    void shouldReturnResponseWithSalesman_whenDataTypeEquals001() {
        String line = "001ç1234567891234çPedroç50000";
        LineModel lineModelResponse = entityMapper.mapToEntity(line);
        Assertions.assertEquals("Pedro", lineModelResponse.getSalesman().getName());
        Assertions.assertEquals(Salesman.class, lineModelResponse.getSalesman().getClass());
    }

    @Test
    void shouldReturnResponseWithCustomer_whenDataTypeEquals002() {
        String line = "002ç2345675434544345çJose da SilvaçRural";
        LineModel lineModelResponse = entityMapper.mapToEntity(line);
        Assertions.assertEquals("2345675434544345", lineModelResponse.getCustomer().getCnpj());
        Assertions.assertEquals(Customer.class, lineModelResponse.getCustomer().getClass());
    }

    @Test
    void shouldReturnResponseWithSale_whenDataTypeEquals003() {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        LineModel lineModelResponse = entityMapper.mapToEntity(line);
        Assertions.assertEquals("10", lineModelResponse.getSale().getId());
        Assertions.assertEquals(Sale.class, lineModelResponse.getSale().getClass());
    }

    @Test
    void dataTypeChecker() {
        String line = "001ç1234567891234çPedroç50000";
        String type = entityMapper.dataTypeChecker(line);
        Assertions.assertEquals("001", type);
    }

    @Test
    void createSale_whenSalesmanNameContainsCedilha() {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çLourenço";
        Sale sale = entityMapper.createSale(line);
        Assertions.assertEquals("Lourenço", sale.getSalesmanName());
    }
}