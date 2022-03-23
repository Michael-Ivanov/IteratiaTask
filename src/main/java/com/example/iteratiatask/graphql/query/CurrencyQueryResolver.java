package com.example.iteratiatask.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.service.CurrencyDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Resolves GraphQL queries for 'Currency' objects.
 * Returning all Currencies from db storage
 * Returning Currency by its char code
 */

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

    public Currency getCurrency(String charCode) {
        return dbService.getByCharCode(charCode);
    }
}
