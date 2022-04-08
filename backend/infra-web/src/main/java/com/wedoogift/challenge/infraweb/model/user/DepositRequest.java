package com.wedoogift.challenge.infraweb.model.user;

import com.wedoogift.challenge.infraweb.model.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DepositRequest extends Request {
    @NotNull
    private Integer userId;
    @NotNull
    @Min(0)
    private Float deposit;

    public DepositRequest() {
    }

    public DepositRequest(Integer userId, Float deposit) {
        this.userId = userId;
        this.deposit = deposit;
    }

    public Integer getUserId() {
        return userId;
    }

    public Float getDeposit() {
        return deposit;
    }
}

