package me.test.currencyparser.exceptions;

public class CurrencyCodeNotFound extends RuntimeException {

    public CurrencyCodeNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyCodeNotFound(String message) {
        super(message);
    }
}
