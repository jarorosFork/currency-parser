package me.test.currencyparser.controller;

import lombok.RequiredArgsConstructor;
import me.test.currencyparser.model.LogEntry;
import me.test.currencyparser.repository.LogEntryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LogEntryController {
    private final LogEntryRepository logEntryRepository;
    public static final String URL = "/log";

    @GetMapping(URL)
    public ModelAndView list(ModelAndView modelAndView){
        List<LogEntry> result = logEntryRepository.findAll();
        modelAndView.getModel().put("logEntries", result);
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @PutMapping("/log/{id}/username")
    public void updateLogEntryUsername(@PathVariable Long id, @RequestParam String username) {
        logEntryRepository.updateUsernameById(id, username);
    }
}
