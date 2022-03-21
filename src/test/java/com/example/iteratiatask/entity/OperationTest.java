package com.example.iteratiatask.entity;

import com.example.iteratiatask.service.CurrencyDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OperationTest {

    @Autowired
    private CurrencyDBService currencyDBService;

    @Test
    public void shouldCorrectlyRoundResultSum() {
        Currency currency1 = currencyDBService.getByCharCode("USD");
        Currency currency2 = currencyDBService.getByCharCode("JPY");
        Operation operation = new Operation(currency1, currency2, 100d);
        assertEquals(2, BigDecimal.valueOf(operation.getResultSum()).scale(), "Should be equal to 2");
    }
}