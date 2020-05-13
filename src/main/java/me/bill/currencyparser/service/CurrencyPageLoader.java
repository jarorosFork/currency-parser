package me.bill.currencyparser.service;

import lombok.SneakyThrows;
import org.jsoup.helper.HttpConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CurrencyPageLoader {
    @Value("${currency.source.url}")
    public String PAGE_URL;

    @SneakyThrows
    public String loadCurrencyPage() {
        return HttpConnection.connect(PAGE_URL).get().html();
    }
}
