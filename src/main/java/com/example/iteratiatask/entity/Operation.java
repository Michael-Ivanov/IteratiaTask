package com.example.iteratiatask.entity;

import javax.persistence.*;
import java.util.Objects;

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
    private String code1;

    @Column(name = "currency_to")
    private String code2;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "sum_to_exchange")
    private Integer sumToExchange;

    @Column(name = "result_sum")
    private Double resultSum;

    public Operation() {
    }

    public Operation(String date, String code1, String code2, Double exchangeRate, Integer sumToExchange) {
        this.date = date;
        this.code1 = code1;
        this.code2 = code2;
        this.exchangeRate = exchangeRate;
        this.sumToExchange = sumToExchange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Integer getSumToExchange() {
        return sumToExchange;
    }

    public void setSumToExchange(Integer sumToExchange) {
        this.sumToExchange = sumToExchange;
    }

    public Double getResultSum() {
        return resultSum;
    }

    public void setResultSum(Double resultSum) {
        this.resultSum = resultSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(id, operation.id) && Objects.equals(date, operation.date) && Objects.equals(code1, operation.code1) && Objects.equals(code2, operation.code2) && Objects.equals(exchangeRate, operation.exchangeRate) && Objects.equals(sumToExchange, operation.sumToExchange) && Objects.equals(resultSum, operation.resultSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, code1, code2, exchangeRate, sumToExchange, resultSum);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", code1='" + code1 + '\'' +
                ", code2='" + code2 + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", sumToExchange=" + sumToExchange +
                ", resultSum=" + resultSum +
                '}';
    }
}
