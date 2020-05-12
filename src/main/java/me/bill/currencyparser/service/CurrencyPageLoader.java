package me.bill.currencyparser.service;

import lombok.SneakyThrows;
import org.jsoup.helper.HttpConnection;
import org.springframework.stereotype.Component;

@Component
public class CurrencyPageLoader {
    public static final String PAGE_URL = "https://en.wikipedia.org/wiki/ISO_4217";

    @SneakyThrows
    public String loadCurrencyPage() {
        return HttpConnection.connect(PAGE_URL).get().html();
    }
}
