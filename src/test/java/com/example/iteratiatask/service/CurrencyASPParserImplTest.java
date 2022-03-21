package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.entity.ExchangeRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyASPParserImplTest {

    @Autowired
    private CurrencyASPParser parser;

    @Test
    public void shouldParseUrlDocument() {
        // simple test to check if parser works and returns list
        List<Currency> list = parser.getAll();
        assertFalse(list.isEmpty());
        assertNotNull(list.get(new Random().nextInt(list.size() - 1)));
        System.out.println(list);
    }

    @Test
    public void shouldFetchValueByCurrencyId() {
        String id = "R01010"; // Australian dollar
        ExchangeRate rate = parser.getExchangeRateById(id);
        System.out.println(rate);
    }
}