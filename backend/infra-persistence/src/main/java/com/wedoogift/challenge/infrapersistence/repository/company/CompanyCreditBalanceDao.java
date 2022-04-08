package com.wedoogift.challenge.infrapersistence.repository.company;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompanyCreditBalanceDao extends CrudRepository<CompanyCreditBalanceEntity, Integer> {

    @Modifying
    @Query("UPDATE CompanyCreditBalanceEntity b " +
            "SET b.balance = b.balance - ?2 " +
            "WHERE b.company.id = ?1 AND b.balance - ?2 >= 0")
    int updateBalance(Integer companyId, Float amount);
}
