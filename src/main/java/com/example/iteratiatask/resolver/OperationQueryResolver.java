package com.example.iteratiatask.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.iteratiatask.entity.Operation;
import com.example.iteratiatask.service.OperationDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OperationQueryResolver implements GraphQLQueryResolver {

    private OperationDBService dbService;

    @Autowired
    public OperationQueryResolver(OperationDBService dbService) {
        this.dbService = dbService;
    }

    public List<Operation> getOperations() {
        return dbService.getAll();
    }

    public Optional<Operation> getOperation(Long id) {
        return dbService.getById(id);
    }
}
