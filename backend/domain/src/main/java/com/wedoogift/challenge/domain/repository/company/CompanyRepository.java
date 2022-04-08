package com.wedoogift.challenge.domain.repository.company;

import java.util.Optional;

public interface CompanyRepository {
    Optional<CompanyDto> get(Integer id);
    Optional<CompanyDto> getWithCreditBalance(Integer id);
    int updateBalance(Integer companyId, Float amount);
}
