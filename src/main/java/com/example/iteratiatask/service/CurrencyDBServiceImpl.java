package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyDBServiceImpl implements CurrencyDBService {

    private final CurrencyRepository repository;
    private final CurrencyASPParser parser;

    @Autowired
    public CurrencyDBServiceImpl(CurrencyRepository repository, CurrencyASPParser parser) {
        this.repository = repository;
        this.parser = parser;
    }

    @PostConstruct
    // initial fill 'currencies' table
    private void init() {
        List<Currency> list = getAll();
        // get list of currencies not saved to db
        List<Currency> notPersisted =
                parser.parseAll().stream()
                .filter(currency -> !list.contains(currency))
                .collect(Collectors.toList());
        saveAll(notPersisted);
    }

    @Override
    public List<Currency> getAll() {
        return repository.findAll();
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
    public Optional<Currency> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Currency> getByCharCode(String charCode) {
        return repository.findByCharCode(charCode);
    }
}
