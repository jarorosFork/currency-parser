package me.bill.currencyparser.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class LogEntry {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime requestDateTime;
    private String currencyCode;
    private String clientIpAddress;
}
