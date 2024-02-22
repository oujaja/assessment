package com.kbtg.bootcamp.posttest.User;

import com.kbtg.bootcamp.posttest.Exception.BadRequestException;
import com.kbtg.bootcamp.posttest.Exception.NotFoundException;
import com.kbtg.bootcamp.posttest.Lotto.Lotto;
import com.kbtg.bootcamp.posttest.Lotto.LottoRepository;
import com.kbtg.bootcamp.posttest.User.UserRepository;
import com.kbtg.bootcamp.posttest.UserTicketLotto.UserTicketLotto;
import com.kbtg.bootcamp.posttest.UserTicketLotto.UserTicketLottoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Service
    public class UserService {

    private final UserRepository userRepository;
    private final UserTicketLottoRepository userTicketLottoRepository;
    private final LottoRepository lottoRepository;

    public UserService(UserRepository userRepository, UserTicketLottoRepository userTicketLottoRepository, LottoRepository lottoRepository) {
        this.userRepository = userRepository;
        this.userTicketLottoRepository = userTicketLottoRepository;
        this.lottoRepository = lottoRepository;
    }


    public Map<String, Object> getAllUserLotteries(String userId) {
        List<UserTicketLotto> userTicketLottos = userTicketLottoRepository.findByUserTicketId(userId);

        Map<String, Object> response = new HashMap<>();
        List<String> lotteries = new ArrayList<>();
        int amount = 0;
        for (UserTicketLotto userTicketLotto : userTicketLottos) {
            lotteries.add(userTicketLotto.getLottery_id());
            amount++;
        }

        response.put("User_ID", userId);
        response.put("Lottery_IDs", lotteries);
        response.put("Amount", amount);
        response.put("Cost", amount * 80); // Assuming price is 80

        return response;
    }






    @Transactional
    public ResponseEntity<?> buyLotteryTicket(String userId, String ticketId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Lotto> lottoOptional = lottoRepository.findByTicket(ticketId);

        if (userOptional.isEmpty() || lottoOptional.isEmpty()) {
            throw new BadRequestException("User or lottery not exist");
        }

        UserTicketLotto userTicketLotto = new UserTicketLotto();
        userTicketLotto.setUserTicketId(userId);
        userTicketLotto.setLotteryId(ticketId);
        userTicketLottoRepository.save(userTicketLotto);
        return ResponseEntity.ok().body("Purchase successful");
    }

    @Transactional
    public ResponseEntity<?> cancelPurchase(String userId, String ticketId) {
        List<UserTicketLotto> userTicketLottos = userTicketLottoRepository.findUserTicketLottoByUserTicketIdAndLotteryId(userId, ticketId);

        if (!userTicketLottos.isEmpty()) {
            UserTicketLotto userTicketLottoToDelete = userTicketLottos.getFirst();
            userTicketLottoRepository.delete(userTicketLottoToDelete);
            return ResponseEntity.ok().body("Purchase canceled successfully");
        } else {
            throw new BadRequestException("UserID "+userId+" don't have"+" lottery number "+ticketId);
        }
    }
}



