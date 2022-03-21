package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Operation;
import com.example.iteratiatask.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationDBServiceImpl implements OperationDBService {

    private OperationRepository repository;

    @Autowired
    public OperationDBServiceImpl(OperationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Operation> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Operation> getWeekOperationsByCharCodes(String charCode1, String charCode2) {
        // get all operations by pair of char codes
        List<Operation> operations = repository.getAllByCharCode1AndCharCode2(charCode1, charCode2);
        // set DateTimeFormatter to custom date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateNow = LocalDate.now();
        // stream and filter only last week operations (inclusively), return result
        return operations.stream()
                .filter(operation -> {
                    LocalDate opDate = LocalDate.parse(operation.getDate(), formatter);
                    return opDate.isAfter(dateNow.minus(8, ChronoUnit.DAYS));
                })
                .collect(Collectors.toList());

    }

    @Override
    public Operation getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Operation save(Operation operation) {
        return repository.save(operation);
    }
}
