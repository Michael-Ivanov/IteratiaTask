package com.example.iteratiatask.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NonNull
    @Column(name = "date")
    private String date;

    @NonNull
    @Column(name = "value")
    private Double value;
}
