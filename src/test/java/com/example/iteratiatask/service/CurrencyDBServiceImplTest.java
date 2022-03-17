package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyDBServiceImplTest {

    @Autowired
    private CurrencyDBService currencyService;

    @Test
    public void shouldShowList() {
        List<Currency> list = currencyService.getAll();
        assertNotNull(list);
        System.out.println(list);
    }
}