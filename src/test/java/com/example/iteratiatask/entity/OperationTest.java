package com.example.iteratiatask.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OperationTest {

    private static Operation operation;

    @BeforeAll
    public static void initOperation() {
        Currency currency1 = new Currency(
                "123g",
                "19.02.2022",
                234,
                "USD",
                1,
                "US Dollar",
                103.33
        );
        Currency currency2 = new Currency(
                "124g",
                "19.02.2022",
                235,
                "EUR",
                1,
                "Euro",
                114.44
        );
        operation = new Operation(currency1, currency2, 100d);
    }

    @Test
    public void shouldCorrectlyRoundResultSum() {
        assertEquals(2, BigDecimal.valueOf(operation.getResultSum()).scale(), "Should be equal to 2");
    }
}