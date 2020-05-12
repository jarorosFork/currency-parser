package me.bill.currencyparser.view;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrencyView {
    private String alphabeticCode;
    private String numericCode;
    private String minorUnit;
    private String currency;
}
