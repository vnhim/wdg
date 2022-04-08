package domain.com.wedoogift.challenge.domain.service.company;

import com.wedoogift.challenge.Application;
import com.wedoogift.challenge.domain.model.error.BusinessException;
import com.wedoogift.challenge.domain.model.error.ErrorType;
import com.wedoogift.challenge.domain.repository.company.CompanyRepository;
import com.wedoogift.challenge.domain.service.company.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;
    @Mock
    private CompanyRepository companyRepository;

    @Test
    public void imputeFees_should_update(){
        Mockito.when(companyRepository.updateBalance(1, 2F)).thenReturn(1);
        Assertions.assertDoesNotThrow(() -> companyService.imputeFees(1, 2F));
    }

    @Test
    public void imputeFees_should_throw_exception_when_nothing_updated(){
        Mockito.when(companyRepository.updateBalance(1, 2F)).thenReturn(0);
        BusinessException exc = Assertions.assertThrows(
                BusinessException.class,
                () -> companyService.imputeFees(1, 2F)
        );

        Assertions.assertEquals(ErrorType.INSUFFICIENT_CREDIT, exc.getError());
        Assertions.assertEquals("Company does not have enough credit for this operation", exc.getMessage());
    }
}
