package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyDBServiceImpl implements CurrencyDBService {

    private final CurrencyRepository repository;

    @Autowired
    public CurrencyDBServiceImpl(CurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Currency> getAll() {
        return repository.findAll();
    }

    @Override
    public void save(Currency currency) {

    }

    @Override
    public void saveAll(List<Currency> currencies) {

    }

    @Override
    public void delete(Currency currency) {

    }
}
