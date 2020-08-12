package me.test.currencyparser.service;

import me.test.currencyparser.model.Currency;
import me.test.currencyparser.repository.CurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.io.IOUtil;
import org.mockito.stubbing.Answer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;

class CurrencyParserServiceTest {
    ClassPathResource codesFile = new ClassPathResource("codes.html");
    CurrencyRepository currencyRepository = Mockito.mock(CurrencyRepository.class);
    CurrencyPageLoader currencyPageLoader = Mockito.mock(CurrencyPageLoader.class);
    CurrencyParserService currencyParserService = new CurrencyParserService(currencyRepository, currencyPageLoader);

    @Test
    @SuppressWarnings("unchecked")
    void loadAndPersist() throws IOException {
        when(currencyPageLoader.loadCurrencyPage())
                .thenReturn(String.join("", IOUtil.readLines(codesFile.getInputStream())));
        when(currencyRepository.saveAll(Mockito.anyCollection()))
                .thenAnswer((Answer<List<Currency>>) invocation -> {
            Object[] args = invocation.getArguments();
            return (List<Currency>) args[0];
        });

        List<Currency> result = currencyParserService.loadAndPersist();
        Assertions.assertEquals(179, result.size());
    }
}