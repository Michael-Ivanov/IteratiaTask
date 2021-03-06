package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Operation;
import com.example.iteratiatask.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Spring Data JPA implementation of Operation database service
 */
@Slf4j
@Service
public class OperationDBServiceImpl implements OperationDBService {

    private OperationRepository repository;

    @Autowired
    public OperationDBServiceImpl(OperationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Operation> getAll() {
        log.info("Getting all operations");
        return repository.findAll();
    }

    @Override
    public List<Operation> getWeekOperationsByCharCodes(String charCode1, String charCode2) {
        log.info("Getting list of operations for the last week for {}/{}", charCode1, charCode2);
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
        log.info("Saving operation: {}", operation);
        return repository.save(operation);
    }
}
