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
    private String charCode1;

    @Column(name = "currency_to")
    private String charCode2;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "sum_to_exchange")
    private Double sumToExchange;

    @Column(name = "result_sum")
    private Double resultSum;


    public Operation() {
    }

    public Operation(String date, String charCode1, String charCode2, Double exchangeRate, Double sumToExchange) {
        this.date = date;
        this.charCode1 = charCode1;
        this.charCode2 = charCode2;
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

    public String getCharCode1() {
        return charCode1;
    }

    public void setCharCode1(String code1) {
        this.charCode1 = code1;
    }

    public String getCharCode2() {
        return charCode2;
    }

    public void setCharCode2(String code2) {
        this.charCode2 = code2;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Double getSumToExchange() {
        return sumToExchange;
    }

    public void setSumToExchange(Double sumToExchange) {
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
        return Objects.equals(id, operation.id) && Objects.equals(date, operation.date) && Objects.equals(charCode1, operation.charCode1) && Objects.equals(charCode2, operation.charCode2) && Objects.equals(exchangeRate, operation.exchangeRate) && Objects.equals(sumToExchange, operation.sumToExchange) && Objects.equals(resultSum, operation.resultSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, charCode1, charCode2, exchangeRate, sumToExchange, resultSum);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", code1='" + charCode1 + '\'' +
                ", code2='" + charCode2 + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", sumToExchange=" + sumToExchange +
                ", resultSum=" + resultSum +
                '}';
    }
}
