package com.example.iteratiatask.repository;

import com.example.iteratiatask.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
