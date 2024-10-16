package me.test.currencyparser.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    
    @Getter
    @Setter
    private String username;
}
