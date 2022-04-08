package com.wedoogift.challenge.infraweb.model.user;

import com.wedoogift.challenge.domain.model.user.UserDto;
import com.wedoogift.challenge.domain.model.user.UserGiftDepositDto;
import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;
import com.wedoogift.challenge.infraweb.model.Response;

public class UserResponse extends Response {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String company;
    private final UserBalance balance;

    public String getCompany() {
        return company;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserResponse(Integer id, String firstName, String lastName, String companyName, UserBalance balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = companyName;
        this.balance = balance;
    }

    public static UserResponse fromDto(UserDto dto){
        return new UserResponse(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getCompany().getName(),
                new UserBalance(
                        dto.getMealDepositDtoList() != null ? dto.getMealDepositDtoList() .stream()
                                .mapToDouble(UserMealDepositDto::getDeposit).sum() : 0,
                        dto.getGiftDepositDtoList() != null ? dto.getGiftDepositDtoList().stream()
                                .mapToDouble(UserGiftDepositDto::getDeposit).sum() : 0
                )
        );
    }

    public UserBalance getBalance() {
        return balance;
    }
}
