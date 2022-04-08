package com.wedoogift.challenge.domain.model.user;

import com.wedoogift.challenge.domain.repository.company.CompanyDto;

import java.util.List;

public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<UserMealDepositDto> mealDepositDtoList;

    public List<UserGiftDepositDto> getGiftDepositDtoList() {
        return giftDepositDtoList;
    }

    private List<UserGiftDepositDto> giftDepositDtoList;

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    private CompanyDto company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserDto(
            Integer id,
            String firstName,
            String lastName,
            CompanyDto company,
            List<UserMealDepositDto> mealDepositDtoList,
            List<UserGiftDepositDto> giftDepositDtoList
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.mealDepositDtoList = mealDepositDtoList;
        this.giftDepositDtoList = giftDepositDtoList;
    }

    public List<UserMealDepositDto> getMealDepositDtoList() {
        return mealDepositDtoList;
    }

    public void setMealDepositDtoList(List<UserMealDepositDto> mealDepositDtoList) {
        this.mealDepositDtoList = mealDepositDtoList;
    }
}
