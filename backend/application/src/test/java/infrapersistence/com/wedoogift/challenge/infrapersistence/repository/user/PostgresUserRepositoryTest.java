package infrapersistence.com.wedoogift.challenge.infrapersistence.repository.user;

import com.wedoogift.challenge.Application;
import com.wedoogift.challenge.domain.model.user.UserDto;
import com.wedoogift.challenge.domain.model.user.UserGiftDepositDto;
import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;
import com.wedoogift.challenge.infrapersistence.repository.user.PostgresUserRepository;
import com.wedoogift.challenge.infrapersistence.repository.user.UserDao;
import com.wedoogift.challenge.infrapersistence.repository.user.UserGiftDepositDao;
import com.wedoogift.challenge.infrapersistence.repository.user.UserMealDepositDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

@ContextConfiguration(classes= {PostgresUserRepository.class, Application.class})
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostgresUserRepositoryTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMealDepositDao userMealDepositDao;
    @Autowired
    private UserGiftDepositDao userGiftDepositDao;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostgresUserRepository userRepository;

    @BeforeEach
    public void setup() {
        entityManager.getEntityManager().createNativeQuery(
                "insert into company_info(id, name) values (1, 'wedoogift') "
        ).executeUpdate();

        entityManager.getEntityManager().createNativeQuery(
                "insert into user_info(id, first_name, last_name, company_id) values (1, 'Monica', 'Lewinski', 1) "
        ).executeUpdate();
    }

    @Test
    public void list_should_return_user_with_deposits()
    {
        var t = entityManager.getEntityManager().createNativeQuery("select * from company_credit_balance")
                        .getResultList();

        entityManager.getEntityManager().createNativeQuery(
                "insert into user_meal_deposit(id, user_id, deposit, start_date, end_date)" +
                        "    values (1, 1, 150, NOW(), '2099-09-18') "
        ).executeUpdate();

        List<UserDto> res = userRepository.list(0, 5);
        Assertions.assertEquals(1, res.size());
        Assertions.assertEquals(1, res.get(0).getMealDepositDtoList().size());
        Assertions.assertEquals( 150F, res.get(0).getMealDepositDtoList().get(0).getDeposit());
    }

    @Test
    public void list_should_return_user_with_no_deposits_when_expired()
    {
        entityManager.getEntityManager().createNativeQuery(
                "insert into user_gift_deposit(id, user_id, deposit, start_date, end_date)" +
                        "    values (1, 1, 150, NOW(), '1970-09-18') "
        ).executeUpdate();

        List<UserDto> res = userRepository.list(0,5);
        Assertions.assertEquals(1, res.size());
        Assertions.assertEquals(0, res.get(0).getGiftDepositDtoList().size());
    }

    @Test
    public void mealDeposit_should_add_new_line(){
        var startDate = LocalDateTime.now();
        var endDate = startDate.plusDays(2);
        UserMealDepositDto mealDepositDto = new UserMealDepositDto(
                1,
                125F,
                startDate,
                endDate
        );

        userRepository.deposit(mealDepositDto);
        var t = userMealDepositDao.findById(1);
        Assertions.assertNotNull(t);
        Assertions.assertEquals(125F, t.get().getDeposit());
        Assertions.assertEquals(startDate, t.get().getStartDate());
        Assertions.assertEquals(endDate, t.get().getEndDate());
    }

    @Test
    public void giftDeposit_should_add_new_line(){
        var startDate = LocalDateTime.now();
        var endDate = startDate.plusDays(2);
        UserGiftDepositDto dto = new UserGiftDepositDto(
                1,
                124F,
                startDate,
                endDate
        );

        userRepository.deposit(dto);
        var t = userGiftDepositDao.findById(1);
        Assertions.assertNotNull(t);
        Assertions.assertEquals(124F, t.get().getDeposit());
        Assertions.assertEquals(startDate, t.get().getStartDate());
        Assertions.assertEquals(endDate, t.get().getEndDate());
    }
}
