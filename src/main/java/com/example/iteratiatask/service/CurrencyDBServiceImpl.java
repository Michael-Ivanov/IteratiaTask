package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyDBServiceImpl implements CurrencyDBService {

    private final CurrencyRepository repository;
    private final CurrencyASPParser parser;

    private List<Currency> dailyList;

    @Autowired
    public CurrencyDBServiceImpl(CurrencyRepository repository, CurrencyASPParser parser) {
        this.repository = repository;
        this.parser = parser;
        dailyList = parser.getAll();
    }

    @PostConstruct
    // initial fill 'currencies' table
    private void init() {
        List<Currency> list = getAll();
        // get list of currencies not present in db
        List<Currency> notPersisted =
                dailyList.stream()
                .filter(currency -> !list.contains(currency))
                .collect(Collectors.toList());
        // save list to db
        saveAll(notPersisted);
    }

    @Override
    public List<Currency> getAll() {
        List<Currency> list = repository.findAll();
        list.sort(Comparator.comparing(Currency::getName));
        return list;
    }

    @Override
    public Currency save(Currency currency) {
        return repository.save(currency);
    }

    @Override
    public void saveAll(List<Currency> currencies) {
        repository.saveAll(currencies);
    }

    @Override
    public void delete(Currency currency) {
        repository.delete(currency);
    }

    @Override
    public Currency getById(String id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Currency getByCharCode(String charCode) {
        Currency currency = repository.findByCharCode(charCode);
        String actualExchangeDate = parser.getDate();
        if (!currency.getDate().equals(actualExchangeDate)) {
            currency = parser.getByCharCode(charCode);
            return repository.save(currency);
        }
        return currency;
    }
}
