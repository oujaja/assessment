package com.kbtg.bootcamp.posttest.User;


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
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
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
        return userService.buyLotteryTicket(userId, ticketId);
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<?> cancelPurchase(@PathVariable String userId, @PathVariable String ticketId) {
        return userService.cancelPurchase(userId, ticketId);
    }
}










