package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Operation;

import java.util.List;

public interface OperationDBService {

    List<Operation> getAll();

    List<Operation> getWeekOperationsByCharCodes(String charCode1, String charCode2);

    Operation getById(Long id);

    Operation save(Operation operation);
}
