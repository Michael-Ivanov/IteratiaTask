package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyDBServiceImplTest {

    @Autowired
    private CurrencyDBService currencyService;

    @Test
    public void shouldShowList() {
        List<Currency> list = currencyService.getAll();
        assertNotNull(list);
    }

    @Test
    public void shouldGetCurrencyById() {
        String id = "R01535";
        Currency currency = currencyService
                .getById(id)   // NOK Норвежская крона
                .orElseThrow(() -> new RuntimeException("Unable to get Currency by id " + id));
        assertEquals("NOK", currency.getCharCode());
    }
}