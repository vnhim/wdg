package com.wedoogift.challenge.infrapersistence.repository.user;

import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_meal_deposit")
public class UserMealDepositEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float deposit;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;


    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime endDate;

    @ManyToOne()
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    public UserMealDepositDto toDto(){
        return new UserMealDepositDto(
                this.id,
                this.user.getId(),
                this.deposit,
                startDate, endDate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }


    public Float getDeposit() {
        return deposit;
    }

    public void setDeposit(Float deposit) {
        this.deposit = deposit;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
