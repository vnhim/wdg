package domain.com.wedoogift.challenge.domain.service.user;

import com.wedoogift.challenge.Application;
import com.wedoogift.challenge.domain.model.error.BusinessException;
import com.wedoogift.challenge.domain.model.error.ErrorType;
import com.wedoogift.challenge.domain.model.user.UserDto;
import com.wedoogift.challenge.domain.model.user.UserGiftDepositDto;
import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;
import com.wedoogift.challenge.domain.repository.company.CompanyDto;
import com.wedoogift.challenge.domain.repository.user.UserRepository;
import com.wedoogift.challenge.domain.service.company.CompanyService;
import com.wedoogift.challenge.domain.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;

@SpringBootTest(classes = Application.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private CompanyService companyService;

    @Test
    public void find_ok(){
        Mockito.when(userRepository.get(1)).thenReturn(Optional.of(
            new UserDto(
                    1,
                    "tata",
                    "yoyo",
                    new CompanyDto(1, "totoCar"),
                    null,
                    null
            )
        ));

        var t = userService.find(1);
        Assertions.assertTrue(t.isPresent());
        Assertions.assertEquals(t.get().getLastName(), "yoyo");
    }

    @Test
    public void find_should_return_emptyOptional(){
        Mockito.when(userRepository.get(1)).thenReturn(Optional.empty());
        Assertions.assertTrue(userService.find(1).isEmpty());
    }

    @Test
    public void list_should_return_user(){
        LocalDateTime now = LocalDateTime.now();
        Mockito.when(userRepository.list(0, 5)).thenReturn(List.of(
                new UserDto(
                        1,
                        "tata",
                        "yoyo",
                        new CompanyDto(1, "totoCar"),
                        List.of(
                                new UserMealDepositDto(1, 1, 50F, now, now.plusDays(1)),
                                new UserMealDepositDto(2, 1, 30F, now, now.plusDays(1))
                        ),
                        List.of(
                                new UserGiftDepositDto(1, 1,  10F, now, now.plusDays(1)),
                                new UserGiftDepositDto(2, 1, 5.5F, now, now.plusDays(1))
                        )
                )
        ));

        List<UserDto> res = userService.list(0, 5);
        Assertions.assertEquals(1, res.size());
        Assertions.assertEquals("tata", res.get(0).getFirstName());
        Assertions.assertEquals("yoyo", res.get(0).getLastName());
        Assertions.assertEquals(1, res.get(0).getCompany().getId());
        Assertions.assertEquals("totoCar", res.get(0).getCompany().getName());

        Assertions.assertEquals(2, res.get(0).getGiftDepositDtoList().size());
        Assertions.assertEquals(1, res.get(0).getGiftDepositDtoList().get(0).getId());
        Assertions.assertEquals(10F, res.get(0).getGiftDepositDtoList().get(0).getDeposit());
        Assertions.assertEquals(1, res.get(0).getGiftDepositDtoList().get(0).getUserId());
        Assertions.assertEquals(now.plusDays(1), res.get(0).getGiftDepositDtoList().get(0).getEndDate());
        Assertions.assertEquals(now, res.get(0).getGiftDepositDtoList().get(0).getStartDate());

        Assertions.assertEquals(2, res.get(0).getMealDepositDtoList().size());
        Assertions.assertEquals(1, res.get(0).getMealDepositDtoList().get(0).getId());
        Assertions.assertEquals(50F, res.get(0).getMealDepositDtoList().get(0).getDeposit());
        Assertions.assertEquals(1, res.get(0).getMealDepositDtoList().get(0).getUserId());
        Assertions.assertEquals(now.plusDays(1), res.get(0).getMealDepositDtoList().get(0).getEndDate());
        Assertions.assertEquals(now, res.get(0).getMealDepositDtoList().get(0).getStartDate());
    }

    @Test
    public void list_should_return_emptyList(){
        Mockito.when(userRepository.list(0, 5)).thenReturn(Collections.emptyList());
        List<UserDto> res = userService.list(0, 5);
        Assertions.assertEquals(0, res.size());
    }

    @Test
    public void mealDeposit_shouldThrow_error_when_UserNotFound(){
        var dto = new UserMealDepositDto(1, 1, 50F, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        Mockito.when(userRepository.get(1)).thenReturn(Optional.empty());
        BusinessException exc = Assertions.assertThrows(
                BusinessException.class,
                () -> userService.mealDeposit(dto)
        );
        Assertions.assertEquals(ErrorType.NOT_FOUND, exc.getError());
        Assertions.assertEquals("User not found", exc.getMessage());
    }

    @Test
    public void mealDeposit_should_do_update(){
        var dto = new UserMealDepositDto(1, 1, 50F, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        Mockito.when(userRepository.get(1)).thenReturn(Optional.of(
                new UserDto(
                        1,
                        "tata",
                        "yoyo",
                        new CompanyDto(1, "totoCar"),
                        null,
                        null
                )
        ));
        doNothing().when(userRepository).deposit(dto);
        doNothing().when(companyService).imputeFees(1, 2F);
        Assertions.assertDoesNotThrow(() -> userService.mealDeposit(dto));
    }
}
