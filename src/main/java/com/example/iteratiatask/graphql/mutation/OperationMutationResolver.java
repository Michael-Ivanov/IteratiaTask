package com.example.iteratiatask.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.entity.Operation;
import com.example.iteratiatask.service.CurrencyDBService;
import com.example.iteratiatask.service.OperationDBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Resolves GraphQL mutations for 'Operation' object.
 * Creates new Operation object.
 */

@Slf4j
@Component
public class OperationMutationResolver implements GraphQLMutationResolver {

    private OperationDBService operationDBService;
    private CurrencyDBService currencyDBService;

    @Autowired
    public OperationMutationResolver(OperationDBService operationDBService, CurrencyDBService currencyDBService) {
        this.operationDBService = operationDBService;
        this.currencyDBService = currencyDBService;
    }

    public Operation createOperation(String charCode1, String charCode2, String sum) {
        log.info("Creating new operation: {}/{}, {}", charCode1, charCode2, sum);
        Currency currency1 = currencyDBService.getByCharCode(charCode1);
        Currency currency2 = currencyDBService.getByCharCode(charCode2);
        return operationDBService.save(new Operation(currency1, currency2, Double.parseDouble(sum)));
    }
}
