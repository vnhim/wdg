package com.wedoogift.challenge.infrapersistence.repository.user;

import com.wedoogift.challenge.domain.model.user.UserDto;
import com.wedoogift.challenge.infrapersistence.repository.company.CompanyEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_info")
public class UserEntity {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    @OneToOne
    private CompanyEntity company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    @Where(clause = "end_date > NOW()")
    private List<UserGiftDepositEntity> giftDepositEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    @Where(clause = "end_date > NOW()")
    private List<UserMealDepositEntity> mealDepositEntities;

    public UserDto toDto(){
        return new UserDto(
                this.id,
                this.firstName,
                this.lastName,
                this.company.toDto(),
                this.mealDepositEntities.stream().map(UserMealDepositEntity::toDto).collect(Collectors.toList()),
                this.giftDepositEntities.stream().map(UserGiftDepositEntity::toDto).collect(Collectors.toList())
        );
    }

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

    public List<UserGiftDepositEntity> getGiftDepositEntities() {
        return giftDepositEntities;
    }

    public void setGiftDepositEntities(List<UserGiftDepositEntity> giftDepositEntities) {
        this.giftDepositEntities = giftDepositEntities;
    }

    public List<UserMealDepositEntity> getMealDepositEntities() {
        return mealDepositEntities;
    }

    public void setMealDepositEntities(List<UserMealDepositEntity> mealDepositEntities) {
        this.mealDepositEntities = mealDepositEntities;
    }
}
