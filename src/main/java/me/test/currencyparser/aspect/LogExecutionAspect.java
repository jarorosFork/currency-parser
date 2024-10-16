package me.test.currencyparser.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.test.currencyparser.model.LogEntry;
import me.test.currencyparser.repository.LogEntryRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class LogExecutionAspect {
    private final LogEntryRepository logEntryRepository;

    @Around("@annotation( me.test.currencyparser.annotations.LogExecution )")
    public Object wrapLogExecution(ProceedingJoinPoint pjp) {
        String currencyCode = (String)pjp.getArgs()[0];
        HttpServletRequest request = (HttpServletRequest)pjp.getArgs()[1];
        Object result;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            saveLogEntry(currencyCode, request);
        }
        return result;
    }

    private void saveLogEntry(String currencyCode, HttpServletRequest request) {
        LogEntry logEntry = new LogEntry();
        logEntry.setRequestDateTime(LocalDateTime.now());
        logEntry.setCurrencyCode(currencyCode);
        logEntry.setClientIpAddress(getIpAddress(request));
        logEntryRepository.save(logEntry);
    }

    private String getIpAddress(HttpServletRequest request){
        return Objects.requireNonNullElse(request.getHeader("X-FORWARDED-FOR"), request.getRemoteAddr());
    }
}
