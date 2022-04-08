package com.wedoogift.challenge.infrapersistence.repository.company;

import com.wedoogift.challenge.domain.repository.company.CompanyDto;
import com.wedoogift.challenge.domain.repository.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostgresCompanyRepository implements CompanyRepository {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private CompanyCreditBalanceDao companyCreditBalanceDao;

    @Override
    public Optional<CompanyDto> get(Integer id) {
        return companyDao.findById(id).map(CompanyEntity::toDto);
    }

    @Override
    public Optional<CompanyDto> getWithCreditBalance(Integer id) {
        return companyCreditBalanceDao.findById(id)
                .map(CompanyCreditBalanceEntity::toDto);
    }

    @Override
    public int updateBalance(Integer companyId, Float amount){
        return companyCreditBalanceDao.updateBalance(companyId, amount);
    }
}
