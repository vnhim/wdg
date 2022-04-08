package com.wedoogift.challenge.domain.service.deposit;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Component
public class DepositService {
    public LocalDateTime calculateMealDepositExpirationDate(LocalDateTime startDate){
        return LocalDate.of(startDate.plusYears(1).getYear(), Month.MARCH, 1).atStartOfDay();
    }

    public LocalDateTime calculateGiftDepositExpirationDate(LocalDateTime startDate){
        return LocalDate.of(
                startDate.plusYears(1).getYear(),
                startDate.plusYears(1).getMonth(),
                startDate.plusYears(1).getDayOfMonth()).atStartOfDay();
    }
}
