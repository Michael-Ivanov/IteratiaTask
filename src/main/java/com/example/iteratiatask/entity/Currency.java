package com.example.iteratiatask.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @Column(name = "id")
    private String id;

    @EqualsAndHashCode.Exclude
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

    @EqualsAndHashCode.Exclude
    @Column(name = "value")
    private Double value;


}
