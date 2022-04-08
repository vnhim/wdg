package com.wedoogift.challenge.infrapersistence.repository.company;

import com.wedoogift.challenge.domain.repository.company.CompanyCreditBalanceDto;
import com.wedoogift.challenge.domain.repository.company.CompanyDto;

import javax.persistence.*;

@Entity
@Table(name = "company_credit_balance")
public class CompanyCreditBalanceEntity {
    @Id
    private Integer id;
    private Float balance;
    @OneToOne
    @Basic(fetch = FetchType.LAZY)
    private CompanyEntity company;

    public CompanyDto toDto(){
        return new CompanyDto(
                this.company.getId(),
                this.company.getName(),
                new CompanyCreditBalanceDto(
                        this.id,
                        this.balance
                )
        );
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

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}
