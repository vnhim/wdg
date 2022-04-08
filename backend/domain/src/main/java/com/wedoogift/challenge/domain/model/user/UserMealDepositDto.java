package com.wedoogift.challenge.domain.model.user;

import java.time.LocalDateTime;

public class UserMealDepositDto {
    private Integer id;
    private final Integer userId;
    private final Float deposit;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Integer getId() {
        return id;
    }
    public Integer getUserId() {
        return userId;
    }
    public Float getDeposit() {
        return deposit;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public UserMealDepositDto(Integer userId, Float deposit, LocalDateTime startDate, LocalDateTime endDate) {
        this.userId = userId;
        this.deposit = deposit;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserMealDepositDto(Integer id, Integer userId, Float deposit, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.userId = userId;
        this.deposit = deposit;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
