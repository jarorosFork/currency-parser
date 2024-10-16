package me.test.currencyparser.repository;

import me.test.currencyparser.model.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {

    @Modifying
    @Query("UPDATE LogEntry l SET l.username = :username WHERE l.id = :id")
    void updateUsernameById(@Param("id") Long id, @Param("username") String username);
}
