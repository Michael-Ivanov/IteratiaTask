package com.example.iteratiatask.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @NonNull
    @Column(name = "id")
    private String id;

    @NonNull
    @Column(name = "num_code")
    private Integer numCode;

    @NonNull
    @Column(name = "char_code")
    private String charCode;

    @NonNull
    @Column(name = "nominal")
    private Integer nominal;

    @NonNull
    @Column(name = "name")
    private String name;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "currency_id")
    private List<ExchangeRate> rates;

    public ExchangeRate getLastRate() {
        if (rates == null || rates.isEmpty()) {
            return null;
        } else {
            // return last element
            return rates.get(rates.size() - 1);
        }
    }

    public void addExchangeRate(ExchangeRate rate) {
        if (rates == null) {
            rates = new ArrayList<>();
        }
        if (!rates.contains(rate)) {
            rates.add(rate);
        }
    }


}
