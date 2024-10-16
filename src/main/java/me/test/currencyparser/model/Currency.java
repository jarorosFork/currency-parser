package me.test.currencyparser.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue
    private Long id;

    private String alphabeticCode;
    private String numericCode;
    private String minorUnit;
    private String currency;

}
