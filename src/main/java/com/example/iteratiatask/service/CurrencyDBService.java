package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;

import java.util.List;

public interface CurrencyDBService {

    List<Currency> getAll();

    void save(Currency currency);

    void saveAll(List<Currency> currencies);

    void delete(Currency currency);

}
