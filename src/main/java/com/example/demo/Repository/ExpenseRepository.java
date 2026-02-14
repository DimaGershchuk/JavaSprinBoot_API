package com.example.demo.Repository;

import com.example.demo.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserUsernameOrderByDateDesc(String username);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.username = :username")
    Double getTotalAmountByUsername(@Param("username") String username);

    @Query("""
        SELECT e.category.name, SUM(e.amount)
        FROM Expense e
        WHERE e.user.username = :username
        AND e.date BETWEEN :start AND :end
        GROUP BY e.category.name
    """)
    List<Object[]> getCategoryTotalsByPeriod(
            @Param("username") String username,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    @Query("""
            SELECT  sum(e.amount) 
                    FROM  Expense e 
                    WHERE e.user.username = :username AND e.date BETWEEN :start AND :end""")
    Double getTotalSpentForPeriod(String username, LocalDate start, LocalDate end);
}
