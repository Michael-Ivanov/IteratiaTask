package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.entity.ExchangeRate;
import com.example.iteratiatask.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
    // initial fill 'currencies' and 'exchange_rates' tables
    private void init() {
        log.info("Checking for currencies not present in DB");
        List<Currency> list = getAll();
        // get list of currencies not present in db
        List<Currency> notPersisted =
                parser.getAll().stream()
                        .filter(currency -> !list.contains(currency))
                        .collect(Collectors.toList());
        // fetch exchange rate from bank page for each element, add
        notPersisted.forEach(elem -> {
            ExchangeRate rate = parser.getExchangeRateById(elem.getId());
            elem.addExchangeRate(rate);
        });
        // save list to db
        saveAll(notPersisted);
    }

    @Override
    public List<Currency> getAll() {
        log.info("Getting all currencies");
        List<Currency> list = repository.findAll();
        // sort list by Currency name
        list.sort(Comparator.comparing(Currency::getName));
        return list;
    }

    @Override
    public Currency save(Currency currency) {
        log.info("Saving currency: {}", currency);
        return repository.save(currency);
    }

    @Override
    public void saveAll(List<Currency> currencies) {
        log.info("Saving list of currencies: {}", currencies);
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
        log.info("Getting Currency by charCode {}", charCode);
        // find Currency in db
        Currency currency = repository.findByCharCode(charCode)
                .orElseThrow(() -> new RuntimeException("Error. Cannot find currency with charCode "
                        + "'" + charCode + "'"));
        ExchangeRate lastRate = currency.getLastRate();
        // get actual exchange date
        String actualExchangeDate = parser.getDate();
        // if there is no exchange rate for the actual date...
        if (lastRate == null || !actualExchangeDate.equals(lastRate.getDate())) {
            // fetch new exchange rate from bank page, add to currency, save and return
            ExchangeRate newRate = parser.getExchangeRateById(currency.getId());
            currency.addExchangeRate(newRate);
            return repository.save(currency);
        }
        return currency;
    }
}
