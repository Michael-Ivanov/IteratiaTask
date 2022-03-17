package com.example.iteratiatask.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "date")
    private String date;

    @Column(name = "num_code")
    private Integer numCode;

    @Column(name = "char_code")
    private String charCode;

    @Column(name = "nominal")
    private Integer nominal;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Double value;

    public Currency() {
    }

    public Currency(String id, String date, Integer numCode, String charCode, Integer nominal, String name, Double value) {
        this.id = id;
        this.date = date;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) && Objects.equals(date, currency.date) && Objects.equals(numCode, currency.numCode) && Objects.equals(charCode, currency.charCode) && Objects.equals(nominal, currency.nominal) && Objects.equals(name, currency.name) && Objects.equals(value, currency.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, numCode, charCode, nominal, name, value);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", numCode=" + numCode +
                ", charCode='" + charCode + '\'' +
                ", nominal=" + nominal +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
