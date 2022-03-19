package com.example.iteratiatask.repository;

import com.example.iteratiatask.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

    Currency findByCharCode(String charCode);
}
