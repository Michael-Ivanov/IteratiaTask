package com.example.iteratiatask.repository;

import com.example.iteratiatask.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

    Optional<Currency> findByCharCode(String charCode);
}
