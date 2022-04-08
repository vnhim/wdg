package com.wedoogift.challenge.domain.repository.company;

public class CompanyCreditBalanceDto {
    private Integer id;
    private Float balance;

    public CompanyCreditBalanceDto(Integer id, Float balance) {
        this.id = id;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
