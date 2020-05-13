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
        Assertions.assertEquals(CODE, currencyView.getAlphabeticCode());
        Assertions.assertEquals("784", currencyView.getNumericCode());
        Assertions.assertEquals("2", currencyView.getMinorUnit());
        Assertions.assertEquals("United Arab Emirates dirham", currencyView.getCurrency());
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnFailedValidation() {
        Map<String, String> response = (Map<String, String>)testRestTemplate.getForEntity(CurrencyController.URL.replace("{code}", CODE_BAD_CODE_WITH_CHAR), Map.class).getBody();
        Assertions.assertTrue(response.get("message").contains("getCurrency.code"), "Message is no ");
    }

}
