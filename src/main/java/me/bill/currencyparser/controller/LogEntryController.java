package me.bill.currencyparser.controller;

import lombok.RequiredArgsConstructor;
import me.bill.currencyparser.model.LogEntry;
import me.bill.currencyparser.repository.LogEntryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LogEntryController {
    private final LogEntryRepository logEntryRepository;
    public static final String URL = "/log/list";

    @GetMapping(URL)
    public ModelAndView list(ModelAndView modelAndView){
        List<LogEntry> result = logEntryRepository.findAll();
        modelAndView.getModel().put("logEntries", result);
        modelAndView.setViewName("list");
        return modelAndView;
    }
}
