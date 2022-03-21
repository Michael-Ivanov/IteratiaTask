package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.entity.ExchangeRate;

import java.util.List;

public interface CurrencyASPParser {

    List<Currency> getAll();

    String getDate();

    Currency getByCharCode(String charCode);

    ExchangeRate getExchangeRateById(String id);
}
