package com.wedoogift.challenge.infrapersistence.repository.company;

import com.wedoogift.challenge.domain.repository.company.CompanyDto;

import javax.persistence.*;

@Entity
@Table(name = "company_info")
public class CompanyEntity {
    @Id
    private Integer id;
    private String name;

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

    public CompanyDto toDto(){
        return new CompanyDto(
                this.id,
                this.name
        );
    }
}
