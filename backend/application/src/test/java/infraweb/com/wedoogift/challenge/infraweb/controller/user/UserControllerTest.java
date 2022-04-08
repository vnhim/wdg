package infraweb.com.wedoogift.challenge.infraweb.controller.user;

import com.wedoogift.challenge.Application;
import com.wedoogift.challenge.domain.model.error.BusinessException;
import com.wedoogift.challenge.domain.model.error.ErrorType;
import com.wedoogift.challenge.domain.model.user.UserDto;
import com.wedoogift.challenge.domain.model.user.UserGiftDepositDto;
import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;
import com.wedoogift.challenge.domain.repository.company.CompanyDto;
import com.wedoogift.challenge.infraweb.model.user.DepositRequest;
import com.wedoogift.challenge.infraweb.model.user.UserBalance;
import com.wedoogift.challenge.infraweb.model.user.UserResponse;
import com.wedoogift.challenge.domain.service.user.UserService;
import com.wedoogift.challenge.infraweb.controller.user.UserController;
import com.wedoogift.challenge.infraweb.model.user.UserListRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @InjectMocks
    private UserController userController;
    @MockBean
    private UserService userService;

    @Test
    public void list_should_return_error_when_unexpected_exception() {
        Mockito.when(userService.list(0, 5)).thenThrow(NullPointerException.class);
        given().port(port)
                .contentType(ContentType.JSON)
                .body(new UserListRequest(0, 5))
        .when()
                .post("/api/user/list")
        .then()
                .statusCode(500)
                .body("code", equalTo(ErrorType.INTERNAL_ERROR.code))
                .body("message", equalTo(ErrorType.INTERNAL_ERROR.message));
    }

    @Test
    public void list_should_return_error_when_count_tooBig() {
        given().port(port)
                .contentType(ContentType.JSON)
                .body(new UserListRequest(0, 200))
        .when()
                .post("/api/user/list")
        .then()
                .statusCode(400)
                .body("code", equalTo(ErrorType.BAD_REQUEST.code))
                .body("message", equalTo("count: must be less than or equal to 20"));
    }

    @Test
    public void list_should_return_error_when_page_lessThan0() {
        given().port(port)
                .contentType(ContentType.JSON)
                .body(new UserListRequest(-10, 2))
        .when()
                .post("/api/user/list")
        .then()
                .statusCode(400)
                .body("code", equalTo(ErrorType.BAD_REQUEST.code))
                .body("message", equalTo("page: must be greater than or equal to 0"));
    }

    @Test
    public void list_should_return_user() {
        LocalDateTime now = LocalDateTime.now();
        Mockito.when(userService.list(0, 5)).thenReturn(List.of(
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
        given().port(port)
                .contentType(ContentType.JSON)
                .body(new UserListRequest(0, 5))
        .when()
                .post("/api/user/list")
        .then()
                .statusCode(200)
                .body("get(0).id", equalTo(1))
                .body("get(0).firstName", equalTo("tata"))
                .body("get(0).lastName", equalTo("yoyo"))
                .body("get(0).company", equalTo("totoCar"))
                .body("get(0).balance.meal", equalTo(80F))
                .body("get(0).balance.gift", equalTo(15.5F))
        ;
    }


    @Test
    public void mealDeposit_should_return_error_when_deposit_lessThan0() {
        given().port(port)
                .contentType(ContentType.JSON)
                .body(new DepositRequest(1, -2F))
        .when()
                .put("/api/user/deposit/meal")
        .then()
                .statusCode(400)
                .body("code", equalTo(ErrorType.BAD_REQUEST.code))
                .body("message", equalTo("deposit: must be greater than or equal to 0"));
    }

    @Test
    public void mealDeposit_should_return201() {
        Mockito.doNothing().when(userService).mealDeposit(Mockito.any(UserMealDepositDto.class));
        given().port(port)
                .contentType(ContentType.JSON)
                .body(new DepositRequest(1, 2F))
        .when()
                .put("/api/user/deposit/meal")
        .then()
                .statusCode(201);
    }

    @Test
    public void mealDeposit_should_return_error_when_company_hasNotEnoughCredit() {
        Mockito.doThrow(new BusinessException(ErrorType.INSUFFICIENT_CREDIT))
                .when(userService).mealDeposit(Mockito.any(UserMealDepositDto.class));

        given().port(port)
                .contentType(ContentType.JSON)
                .body(new DepositRequest(1, 2F))
        .when()
                .put("/api/user/deposit/meal")
        .then()
                .statusCode(412)
                .body("code", equalTo(ErrorType.INSUFFICIENT_CREDIT.code));
    }
}
