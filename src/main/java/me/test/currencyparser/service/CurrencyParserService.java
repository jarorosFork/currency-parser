package me.test.currencyparser.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.test.currencyparser.model.Currency;
import me.test.currencyparser.repository.CurrencyRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CurrencyParserService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyPageLoader currencyPageLoader;

    @PostConstruct
    public List<Currency> loadAndPersist() {
        List<Currency> currencies = loadCurrencies();
        return currencyRepository.saveAll(currencies);
    }

    private List<Currency> loadCurrencies() {
        String htmlPage = currencyPageLoader.loadCurrencyPage();
        Document doc = Jsoup.parseBodyFragment(htmlPage);
        Element[] elementArray = parseTableElements(doc);
        return parseToCurrencyEntites(elementArray);
    }

    private List<Currency> parseToCurrencyEntites(Element[] elementArray) {
        return Arrays.stream(elementArray)
                .map(element -> parseTdElementToCurrency(element.getElementsByTag("td")))
                .collect(toList());
    }

    private Element[] parseTableElements(Document doc) {
        Elements elements = parseTableBody(doc);
        return skipFirst(elements.toArray(new Element[0]));
    }


    private Element[] skipFirst(Element[] elementArray) {
        return Arrays.copyOfRange(elementArray, 1, elementArray.length);
    }

    private Elements parseTableBody(Document doc) {
        return doc.getElementsByClass("wikitable sortable").first()
                .getElementsByTag("tbody").first()
                .getElementsByTag("tr");
    }

    private Currency parseTdElementToCurrency(Elements elements) {
        Currency currency = new Currency();
        currency.setAlphabeticCode(elements.get(0).childNode(0).toString());
        currency.setNumericCode(elements.get(1).childNode(0).toString());
        currency.setMinorUnit(elements.get(2).childNode(0).toString());
        currency.setCurrency(parseCurrencyCell(elements.get(3)));
        return currency;
    }

    private String parseCurrencyCell(Element currency) {
        Element aHrefElement = currency.getElementsByTag("a").first();
        return Objects.requireNonNullElse(aHrefElement, currency).childNode(0).toString();
    }

}
