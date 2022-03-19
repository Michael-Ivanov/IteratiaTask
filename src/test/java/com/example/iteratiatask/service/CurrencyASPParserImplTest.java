package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
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
    // simple test to check if parser works and returns list
    public void shouldParseUrlDocument() {
        List<Currency> list = parser.getAll();
        assertFalse(list.isEmpty());
        assertNotNull(list.get(new Random().nextInt(list.size() - 1)));
        System.out.println(list);
    }
}