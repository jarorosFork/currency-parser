package me.bill.currencyparser.controller;

import lombok.RequiredArgsConstructor;
import me.bill.currencyparser.annotations.LogExecution;
import me.bill.currencyparser.exceptions.CurrencyCodeNotFound;
import me.bill.currencyparser.repository.CurrencyRepository;
import me.bill.currencyparser.view.CurrencyView;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;
import java.util.Objects;

import static java.lang.String.format;

@RestController
@Validated
@RequiredArgsConstructor
public class CurrencyController {
    public static final String URL = "/currency/{code}";
    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;

    @LogExecution
    @GetMapping(URL)
    public CurrencyView getCurrency(@PathVariable("code")
                                    @Size(min = 3, max = 3)
                                    @NotBlank
                                    @Pattern(regexp = "[A-Z]+") String code, HttpServletRequest request) {
        return currencyRepository.findOneByAlphabeticCode(code)
                .filter(Objects::nonNull)
                .map(currency -> modelMapper.map(currency, CurrencyView.class))
                .orElseThrow(() -> new CurrencyCodeNotFound(format("Currency for given %s code is not found", code)));
    }
}
