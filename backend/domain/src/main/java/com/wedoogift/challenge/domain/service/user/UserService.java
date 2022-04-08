package com.wedoogift.challenge.domain.service.user;

import com.wedoogift.challenge.domain.model.error.BusinessException;
import com.wedoogift.challenge.domain.model.error.ErrorType;
import com.wedoogift.challenge.domain.model.user.UserDto;
import com.wedoogift.challenge.domain.model.user.UserGiftDepositDto;
import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;
import com.wedoogift.challenge.domain.repository.user.UserRepository;
import com.wedoogift.challenge.domain.service.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyService companyService;

    public Optional<UserDto> find(Integer id){
        return userRepository.get(id);
    }

    public List<UserDto> list(Integer page, Integer count){
        return userRepository.list(page, count);
    }

    @Transactional
    public void mealDeposit(UserMealDepositDto mealDepositDto){
        Optional<UserDto> userDto = userRepository.get(mealDepositDto.getUserId());
        if(userDto.isPresent()){
            userRepository.deposit(mealDepositDto);
            companyService.imputeFees(
                    userDto.get().getCompany().getId(),
                    mealDepositDto.getDeposit()
            );
        }else {
            logger.warn(String.format("User %d not found", mealDepositDto.getUserId()));
            throw new BusinessException(ErrorType.NOT_FOUND, "User not found");
        }
    }

    @Transactional
    public void giftDeposit(UserGiftDepositDto giftDepositDto){
        Optional<UserDto> userDto = userRepository.get(giftDepositDto.getUserId());
        if(userDto.isPresent()){
            userRepository.deposit(giftDepositDto);
            companyService.imputeFees(
                    userDto.get().getCompany().getId(),
                    giftDepositDto.getDeposit()
            );
        }else {
            logger.warn(String.format("User %d not found", giftDepositDto.getUserId()));
            throw new BusinessException(ErrorType.NOT_FOUND, "User not found");
        }
    }

}
