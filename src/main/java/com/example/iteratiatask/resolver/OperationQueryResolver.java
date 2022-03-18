package com.example.iteratiatask.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.entity.Operation;
import com.example.iteratiatask.service.CurrencyDBService;
import com.example.iteratiatask.service.OperationDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OperationQueryResolver implements GraphQLQueryResolver {

    private OperationDBService operationDBService;
    private CurrencyDBService currencyDBService;

    @Autowired
    public OperationQueryResolver(OperationDBService operationDBService, CurrencyDBService currencyDBService) {
        this.operationDBService = operationDBService;
        this.currencyDBService = currencyDBService;
    }

    public List<Operation> getOperations() {
        return operationDBService.getAll();
    }

    public Optional<Operation> getOperation(Long id) {
        return operationDBService.getById(id);
    }

    public Operation getOperation(String charCode1, String charCode2, Double sum) {
        Currency currency1 = currencyDBService.getByCharCode(charCode1).orElseThrow();
        Currency currency2 = currencyDBService.getByCharCode(charCode2).orElseThrow();
        return operationDBService.save(new Operation(currency1, currency2, sum));
    }
}
