package com.example.iteratiatask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "currency_from")
    private String charCode1;

    @Column(name = "currency_to")
    private String charCode2;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "sum_to_exchange")
    private Double sumToExchange;

    @Column(name = "result_sum")
    private Double resultSum;

    public Operation(Currency currency1, Currency currency2, Double sumToExchange) {
        this.date = currency1.getDate();
        this.charCode1 = currency1.getCharCode();
        this.charCode2 = currency2.getCharCode();
        this.exchangeRate = (currency1.getValue() / currency1.getNominal()) / (currency2.getValue() / currency2.getNominal());
        this.sumToExchange = sumToExchange;
        this.resultSum = exchangeRate * sumToExchange;
    }
}
