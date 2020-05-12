package me.bill.currencyparser.exceptions;

public class CurrencyCodeNotValid extends RuntimeException {

    public CurrencyCodeNotValid(String message, Throwable cause) {
        super(message, cause);
    }
}
