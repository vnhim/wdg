package com.wedoogift.challenge.domain.repository.user;

import com.wedoogift.challenge.domain.model.user.UserDto;
import com.wedoogift.challenge.domain.model.user.UserGiftDepositDto;
import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> get(Integer id);
    List<UserDto> list(Integer page, Integer count);
    void deposit(UserMealDepositDto mealDepositDto);
    void deposit(UserGiftDepositDto giftDepositDto);
}
