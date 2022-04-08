package infraweb.com.wedoogift.challenge.infraweb.model.user;

import com.wedoogift.challenge.domain.repository.company.CompanyDto;
import com.wedoogift.challenge.domain.model.user.UserDto;
import com.wedoogift.challenge.domain.model.user.UserGiftDepositDto;
import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;
import com.wedoogift.challenge.infraweb.model.user.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponseTest {
    @Test
    public void fromDto_should_return_validObject(){
        LocalDateTime now = LocalDateTime.now();
        UserDto userDto = new UserDto(
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
        );

        UserResponse res = UserResponse.fromDto(userDto);
        Assertions.assertEquals("tata", res.getFirstName());
        Assertions.assertEquals("yoyo", res.getLastName());
        Assertions.assertEquals("totoCar", res.getCompany());
        Assertions.assertNotNull(res.getBalance());
        Assertions.assertEquals(15.5F, res.getBalance().getGift());
        Assertions.assertEquals(80F, res.getBalance().getMeal());
    }
}
