package com.example.iteratiatask.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.iteratiatask.entity.Operation;
import com.example.iteratiatask.service.OperationDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationQueryResolver implements GraphQLQueryResolver {

    private OperationDBService operationDBService;

    @Autowired
    public OperationQueryResolver(OperationDBService operationDBService) {
        this.operationDBService = operationDBService;
    }

    public List<Operation> getOperations() {
        return operationDBService.getAll();
    }

}
