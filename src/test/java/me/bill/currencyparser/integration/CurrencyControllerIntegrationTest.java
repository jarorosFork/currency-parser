package me.bill.currencyparser.integration;

import lombok.SneakyThrows;
import me.bill.currencyparser.controller.CurrencyController;
import me.bill.currencyparser.view.CurrencyView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CurrencyControllerIntegrationTest extends BaseIntegrationTest {

    public static final String CODE = "AED";
    public static final String CODE_BAD_CODE_WITH_CHAR = "123A";

    @Test
    void shouldReturnValidCurrencyView() throws Exception {
        String jsonResult = mvc.perform(get(CurrencyController.URL.replace("{code}", CODE))
                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        CurrencyView currencyView = objectMapper.readValue(jsonResult, CurrencyView.class);
        Assertions.assertEquals(currencyView.getAlphabeticCode(), CODE);
        Assertions.assertEquals(currencyView.getNumericCode(), "784");
        Assertions.assertEquals(currencyView.getMinorUnit(), "2");
        Assertions.assertEquals(currencyView.getCurrency(), "United Arab Emirates dirham");
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnFailedValidation() {
        Map<String, Object> response = (Map<String, Object>)testRestTemplate.getForEntity(CurrencyController.URL.replace("{code}", CODE_BAD_CODE_WITH_CHAR), Map.class).getBody();
        Assertions.assertEquals("getCurrency.code: must match \"[A-Z]+\", getCurrency.code: size must be between 3 and 3", response.get("message"), "Message is no ");
    }

}
