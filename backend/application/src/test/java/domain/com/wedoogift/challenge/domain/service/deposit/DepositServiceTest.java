package domain.com.wedoogift.challenge.domain.service.deposit;

import com.wedoogift.challenge.Application;
import com.wedoogift.challenge.domain.service.deposit.DepositService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest(classes = Application.class)
public class DepositServiceTest {
    @InjectMocks
    private DepositService depositService;

    @Test
    public void calculateMealDepositExpirationDate_should_return_date(){
        LocalDateTime start = LocalDateTime.of(2022, 1, 1, 10, 10);
        LocalDateTime end = depositService.calculateMealDepositExpirationDate(start);
        Assertions.assertTrue(
                end.isEqual(LocalDateTime.of(2023, 3, 1, 0, 0))
        );
    }

    @Test
    public void calculateGiftDepositExpirationDate_should_return_date(){
        LocalDateTime start = LocalDateTime.of(2022, 1, 1, 10, 10);
        LocalDateTime end = depositService.calculateGiftDepositExpirationDate(start);
        Assertions.assertTrue(
                end.isEqual(LocalDateTime.of(2023, 1, 1, 0, 0))
        );
    }
}
