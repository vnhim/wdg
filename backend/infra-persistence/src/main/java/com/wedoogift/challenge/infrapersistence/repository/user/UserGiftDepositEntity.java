package com.wedoogift.challenge.infrapersistence.repository.user;

import com.wedoogift.challenge.domain.model.user.UserGiftDepositDto;
import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_gift_deposit")
public class UserGiftDepositEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float deposit;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    public UserGiftDepositDto toDto(){
        return new UserGiftDepositDto(
                this.id,
                this.user.getId(),
                this.deposit, startDate, endDate
        );
    }

    public static UserGiftDepositEntity fromDto(UserGiftDepositDto dto){
        UserEntity u1 = new UserEntity();
        u1.setId(dto.getUserId());
        var t = new UserGiftDepositEntity();
        t.setUser(u1);
        t.setDeposit(dto.getDeposit());
        t.setEndDate(dto.getEndDate());
        t.setStartDate(dto.getStartDate());
        return t;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getDeposit() {
        return deposit;
    }

    public void setDeposit(Float deposit) {
        this.deposit = deposit;
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
}
