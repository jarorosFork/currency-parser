package me.test.currencyparser.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
