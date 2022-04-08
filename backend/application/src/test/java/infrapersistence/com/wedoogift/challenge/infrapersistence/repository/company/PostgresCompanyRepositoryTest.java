package infrapersistence.com.wedoogift.challenge.infrapersistence.repository.company;

import com.wedoogift.challenge.Application;
import com.wedoogift.challenge.infrapersistence.repository.company.CompanyDao;
import com.wedoogift.challenge.infrapersistence.repository.company.PostgresCompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes= {PostgresCompanyRepository.class, Application.class})
@DataJpaTest
public class PostgresCompanyRepositoryTest {
    @Autowired
    private CompanyDao dao;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostgresCompanyRepository companyRepository;

    @BeforeEach
    public void setup() {
        entityManager.getEntityManager().createNativeQuery(
                "insert into company_info(id, name) values (1, 'wedoogift') "
        ).executeUpdate();
        entityManager.getEntityManager().createNativeQuery(
                "insert into company_credit_balance(id, company_id, balance) values (1, 1, 500) "
        ).executeUpdate();
    }


    @Test
    public void get_should_return_dto(){
        var t = companyRepository.get(1);
        Assertions.assertTrue(t.isPresent());
        Assertions.assertEquals(1, t.get().getId());
        Assertions.assertEquals("wedoogift", t.get().getName());
        Assertions.assertNull(t.get().getBalanceDto());
    }

    @Test
    public void getWithBalance_should_return_dto(){
        var t = companyRepository.getWithCreditBalance(1);
        Assertions.assertTrue(t.isPresent());
        Assertions.assertEquals(1, t.get().getId());
        Assertions.assertEquals("wedoogift", t.get().getName());
        Assertions.assertNotNull(t.get().getBalanceDto());
        Assertions.assertEquals(1, t.get().getBalanceDto().getId());
        Assertions.assertEquals(500, t.get().getBalanceDto().getBalance());
    }

    @Test
    public void updateBalance_should_update_if_enough_credit(){
        var t = companyRepository.updateBalance(1, 300F);
        var r = companyRepository.getWithCreditBalance(1);

        Assertions.assertEquals(1, t);
        Assertions.assertTrue(r.isPresent());
        Assertions.assertEquals(200F, r.get().getBalanceDto().getBalance());
    }

    @Test
    public void updateBalance_should_doNothing_if_not_enough_credit(){
        var t = companyRepository.updateBalance(1, 1000000F);
        var r = companyRepository.getWithCreditBalance(1);

        Assertions.assertEquals(0, t);
        Assertions.assertTrue(r.isPresent());
        Assertions.assertEquals(500F, r.get().getBalanceDto().getBalance());
    }

}
