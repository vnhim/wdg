package com.wedoogift.challenge.infraweb.controller.user;

import com.wedoogift.challenge.domain.model.user.UserGiftDepositDto;
import com.wedoogift.challenge.domain.model.user.UserMealDepositDto;
import com.wedoogift.challenge.domain.service.deposit.DepositService;
import com.wedoogift.challenge.infraweb.model.user.DepositRequest;
import com.wedoogift.challenge.infraweb.model.user.UserResponse;
import com.wedoogift.challenge.domain.service.user.UserService;
import com.wedoogift.challenge.infraweb.controller.AbstractController;
import com.wedoogift.challenge.infraweb.model.user.UserListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController extends AbstractController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepositService depositService;

    @PostMapping(
            path = "list",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<UserResponse> list(@RequestBody UserListRequest request) {
        validate(request);
        return userService.list(request.getPage(), request.getCount()).stream()
                .map(UserResponse::fromDto).collect(Collectors.toList());
    }

    @PutMapping(
            path = "deposit/meal",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> mealDeposit(@RequestBody DepositRequest request){
        super.validate(request);
        LocalDateTime startDate = LocalDateTime.now();
        userService.mealDeposit(
            new UserMealDepositDto(
                    request.getUserId(),
                    request.getDeposit(),
                    startDate,
                    depositService.calculateMealDepositExpirationDate(startDate)
            )
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(
            path = "deposit/gift",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> giftDeposit(@Valid @RequestBody DepositRequest request){
        super.validate(request);
        LocalDateTime startDate = LocalDateTime.now();
        userService.giftDeposit(
                new UserGiftDepositDto(
                        request.getUserId(),
                        request.getDeposit(),
                        startDate,
                        depositService.calculateGiftDepositExpirationDate(startDate)
                )
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
