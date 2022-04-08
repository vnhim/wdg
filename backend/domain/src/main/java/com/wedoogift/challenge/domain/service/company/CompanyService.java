package com.wedoogift.challenge.domain.service.company;

import com.wedoogift.challenge.domain.model.error.BusinessException;
import com.wedoogift.challenge.domain.model.error.ErrorType;
import com.wedoogift.challenge.domain.repository.company.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyService {
    private final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository companyRepository;

    public void imputeFees(Integer companyId, Float amount){
        if(companyRepository.updateBalance(companyId, amount) != 1){
            logger.warn(String.format("Company %d does not have enough credit for this operation", companyId));
            throw new BusinessException(
                    ErrorType.INSUFFICIENT_CREDIT,
                    "Company does not have enough credit for this operation"
            );
        }
    }
}
