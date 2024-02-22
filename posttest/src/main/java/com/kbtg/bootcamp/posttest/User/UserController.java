package com.kbtg.bootcamp.posttest.User;


import com.kbtg.bootcamp.posttest.Exception.BadRequestException;
import com.kbtg.bootcamp.posttest.Exception.NotFoundException;
import com.kbtg.bootcamp.posttest.UserTicketLotto.UserTicketLotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/lotteries")
    public ResponseEntity<Map<String, Object>> getAllLotteriesByUser(@PathVariable String userId) {
        Map<String, Object> response = userService.getAllUserLotteries(userId);
        if (userId.length() != 10) {
            throw new BadRequestException("Invalid user ID. Length should be 10.");
        }

        if (response.isEmpty()) {
            throw new NotFoundException("No User ID "+userId);
        }

        return ResponseEntity.ok().body(response);
    }



    @GetMapping
    List<User> getUser(){
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }



    @PostMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<?> buyLotteryTicket(@PathVariable String userId, @PathVariable String ticketId) {
        if (userId.length() != 10) {
            throw new BadRequestException("Invalid user ID. Length should be 10.");
        }
        if (ticketId.length() != 6) {
            throw new BadRequestException("Invalid ticket ID. Length should be 6.");
        }
        return userService.buyLotteryTicket(userId, ticketId);
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<?> cancelPurchase(@PathVariable String userId, @PathVariable String ticketId) {
        if (userId.length() != 10) {
            throw new BadRequestException("Invalid user ID. Length should be 10.");
        }
        if (ticketId.length() != 6) {
            throw new BadRequestException("Invalid ticket ID. Length should be 6.");
        }
        return userService.cancelPurchase(userId, ticketId);
    }
}










