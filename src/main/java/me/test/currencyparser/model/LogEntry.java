package me.test.currencyparser.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    
    @Getter
    @Setter
    private String username;
}
