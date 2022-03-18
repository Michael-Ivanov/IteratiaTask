package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Operation;
import com.example.iteratiatask.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Operation> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Operation operation) {
        repository.save(operation);
    }
}
