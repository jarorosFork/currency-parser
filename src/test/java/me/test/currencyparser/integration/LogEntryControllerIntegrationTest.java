package me.test.currencyparser.integration;

import me.test.currencyparser.controller.CurrencyController;
import me.test.currencyparser.controller.LogEntryController;
import me.test.currencyparser.model.LogEntry;
import me.test.currencyparser.repository.LogEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LogEntryControllerIntegrationTest extends BaseIntegrationTest {
    @Autowired
    LogEntryRepository logEntryRepository;

    public static final String CODE = "AMD";

    @Test
    void shouldReturnValidCurrencyView() throws Exception {
        logEntryRepository.deleteAll(logEntryRepository.findAll());
        mvc.perform(get(CurrencyController.URL.replace("{code}", CODE))
                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        ModelAndView modelAndViewResult = mvc.perform(get(LogEntryController.URL))
                .andExpect(status().isOk()).andReturn().getModelAndView();
        List<LogEntry> logEntryList = (List<LogEntry>) modelAndViewResult.getModel().get("logEntries");
        Assertions.assertEquals("list", modelAndViewResult.getViewName());
        Assertions.assertEquals(CODE, logEntryList.get(0).getCurrencyCode());
    }

    @Test
    void shouldUpdateLogEntryUsername() throws Exception {
        logEntryRepository.deleteAll(logEntryRepository.findAll());
        mvc.perform(get(CurrencyController.URL.replace("{code}", CODE))
                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        ModelAndView modelAndViewResult = mvc.perform(get(LogEntryController.URL))
                .andExpect(status().isOk()).andReturn().getModelAndView();
        List<LogEntry> logEntryList = (List<LogEntry>) modelAndViewResult.getModel().get("logEntries");
        Long logEntryId = logEntryList.get(0).getId();

        mvc.perform(put("/log/" + logEntryId + "/username")
                .param("username", "newUsername"))
                .andExpect(status().isOk());

        LogEntry updatedLogEntry = logEntryRepository.findById(logEntryId).orElseThrow();
        Assertions.assertEquals("newUsername", updatedLogEntry.getUsername());
    }
}
