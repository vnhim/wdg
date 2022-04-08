package com.wedoogift.challenge.domain.repository.company;

public class CompanyDto {
    private Integer id;
    private String name;
    private CompanyCreditBalanceDto balanceDto;

    public CompanyDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyDto(Integer id, String name, CompanyCreditBalanceDto balanceDto) {
        this.id = id;
        this.name = name;
        this.balanceDto = balanceDto;
    }

    public CompanyCreditBalanceDto getBalanceDto() {
        return balanceDto;
    }

    public void setBalanceDto(CompanyCreditBalanceDto balanceDto) {
        this.balanceDto = balanceDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
