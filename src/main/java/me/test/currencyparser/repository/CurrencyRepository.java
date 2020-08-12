package me.test.currencyparser.repository;

import me.test.currencyparser.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findOneByAlphabeticCode(String code);
}
