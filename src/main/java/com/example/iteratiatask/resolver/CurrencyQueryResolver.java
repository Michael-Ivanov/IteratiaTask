package com.example.iteratiatask.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.service.CurrencyDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CurrencyQueryResolver implements GraphQLQueryResolver {

    private CurrencyDBService dbService;

    @Autowired
    public CurrencyQueryResolver(CurrencyDBService dbService) {
        this.dbService = dbService;
    }

    public List<Currency> getCurrencies() {
        return dbService.getAll();
    }

    public Optional<Currency> getCurrency(String id) {
        return dbService.getById(id);
    }
}
