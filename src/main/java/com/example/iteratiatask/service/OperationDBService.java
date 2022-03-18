package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Operation;

import java.util.List;
import java.util.Optional;

public interface OperationDBService {

    List<Operation> getAll();

    Optional<Operation> getById(Long id);

    Operation save(Operation operation);
}
