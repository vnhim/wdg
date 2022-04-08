package com.wedoogift.challenge.domain.model.user;

import java.time.LocalDateTime;

public class UserGiftDepositDto {
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

    public UserGiftDepositDto(Integer userId, Float deposit, LocalDateTime startDate, LocalDateTime endDate) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
    }

    public UserGiftDepositDto(Integer id, Integer userId, Float deposit, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
    }


}
