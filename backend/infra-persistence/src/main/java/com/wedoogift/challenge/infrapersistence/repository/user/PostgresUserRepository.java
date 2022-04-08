package com.wedoogift.challenge.infrapersistence.repository.user;

import com.wedoogift.challenge.domain.model.user.UserDto;
import com.wedoogift.challenge.domain.model.user.UserGiftDepositDto;
import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;
import com.wedoogift.challenge.domain.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostgresUserRepository implements UserRepository {
    private final Logger logger = LoggerFactory.getLogger(PostgresUserRepository.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMealDepositDao userMealDepositDao;
    @Autowired
    private UserGiftDepositDao userGiftDepositDao;

    @Override
    public Optional<UserDto> get(Integer id) {
        return userDao.findById(id).map(UserEntity::toDto);
    }

    @Override
    public List<UserDto> list(Integer page, Integer count) {
        return userDao.findAll(PageRequest.of(page, count)).stream()
                .map(UserEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public void deposit(UserMealDepositDto mealDepositDto){
        UserEntity u1 = new UserEntity();
        u1.setId(mealDepositDto.getUserId());

        var t = new UserMealDepositEntity();
        t.setUser(u1);
        t.setDeposit(mealDepositDto.getDeposit());
        t.setEndDate(mealDepositDto.getEndDate());
        t.setStartDate(mealDepositDto.getStartDate());
        userMealDepositDao.save(t);
    }

    @Override
    public void deposit(UserGiftDepositDto giftDepositDto) {
        UserEntity u1 = new UserEntity();
        u1.setId(giftDepositDto.getUserId());

        var t = new UserGiftDepositEntity();
        t.setUser(u1);
        t.setDeposit(giftDepositDto.getDeposit());
        t.setEndDate(giftDepositDto.getEndDate());
        t.setStartDate(giftDepositDto.getStartDate());
        userGiftDepositDao.save(t);
    }
}
