package com.example.iteratiatask.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.iteratiatask.entity.Operation;
import com.example.iteratiatask.entity.Stats;
import com.example.iteratiatask.service.OperationDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Resolves GraphQL qureies for 'Stats' object.
 * Returns new Stats object for queried pair of char codes
 */
@Component
public class StatsQueryResolver implements GraphQLQueryResolver {

    private OperationDBService operationDBService;

    @Autowired
    public StatsQueryResolver(OperationDBService operationDBService) {
        this.operationDBService = operationDBService;
    }

    public Stats getStats(String charCode1, String charCode2) {
        List<Operation> operations = operationDBService.getWeekOperationsByCharCodes(charCode1, charCode2);
        return new Stats(operations);
    }
}
