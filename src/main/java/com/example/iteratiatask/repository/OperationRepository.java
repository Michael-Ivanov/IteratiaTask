package com.example.iteratiatask.repository;

import com.example.iteratiatask.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> getAllByCharCode1AndCharCode2(String charCode1, String charCode2);
}
