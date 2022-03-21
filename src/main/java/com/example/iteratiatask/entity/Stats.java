package com.example.iteratiatask.entity;

import lombok.Data;

import java.util.List;

@Data
public class Stats {

    private List<Operation> operations;

    private Double avgRate;

    private Double sumValue;

    public Stats(List<Operation> operations) {
        this.operations = operations;
        this.avgRate = calculateAvgRate(operations);
        this.sumValue = calculateSumValue(operations);
    }

    private Double calculateAvgRate(List<Operation> operations) {
        return operations.stream()
                .mapToDouble(Operation::getExchangeRate)
                .average()
                .orElseThrow();
    }

    private Double calculateSumValue(List<Operation> operations) {
        return operations.stream()
                .mapToDouble(Operation::getSumToExchange)
                .sum();
    }
}
