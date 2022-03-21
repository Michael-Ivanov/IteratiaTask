package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
                .getById(id);  // NOK Норвежская крона
        assertEquals("NOK", currency.getCharCode());
    }

    @Test
    public void shouldSeeEqualsTwoCurrencies() {
        Currency currency1 = currencyService.getByCharCode("USD");
        Currency currency2 = new Currency(
                currency1.getId(),
                currency1.getNumCode(),
                currency1.getCharCode(),
                currency1.getNominal(),
                currency1.getName()
        );
        assertEquals(currency1, currency2);
    }
}