package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.Exception.BadRequestException;
import com.kbtg.bootcamp.posttest.User.User;
import com.kbtg.bootcamp.posttest.User.UserService;
import com.kbtg.bootcamp.posttest.UserTicketLotto.UserTicketLotto;
import com.kbtg.bootcamp.posttest.UserTicketLotto.UserTicketLottoRepository;
import com.kbtg.bootcamp.posttest.Lotto.Lotto;
import com.kbtg.bootcamp.posttest.Lotto.LottoRepository;
import com.kbtg.bootcamp.posttest.User.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserTicketLottoRepository userTicketLottoRepository;

    @Mock
    private LottoRepository lottoRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Should show Purchase successful")
    public void testBuyLotteryTicket_Success() {

        when(userRepository.findById("7852365147")).thenReturn(Optional.of(new User()));
        when(lottoRepository.findByTicket("123456")).thenReturn(Optional.of(new Lotto()));

        ResponseEntity<?> response = userService.buyLotteryTicket("7852365147", "123456");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Purchase successful", response.getBody());
    }

    @Test
    @DisplayName("Should Throw Exception")
    public void testBuyLotteryTicket_UserNotFound() {

        when(userRepository.findById("7852365147")).thenReturn(Optional.empty());


        assertThrows(BadRequestException.class, () -> userService.buyLotteryTicket("7852365147", "123456"));
    }

    @Test
    @DisplayName("Should Throw Exception")
    public void testBuyLotteryTicket_LottoNotFound() {

        when(userRepository.findById("7852365147")).thenReturn(Optional.of(new User()));
        when(lottoRepository.findByTicket("123456")).thenReturn(Optional.empty());


        assertThrows(BadRequestException.class, () -> userService.buyLotteryTicket("7852365147", "123456"));
    }

    @Test
    @DisplayName("Should get all user lottery number and detail")
    public void testGetAllUserLotteries() {
        List<UserTicketLotto> userTicketLottos = new ArrayList<>();
        userTicketLottos.add(new UserTicketLotto(1, "7852365147", "123456"));
        userTicketLottos.add(new UserTicketLotto(2, "7852365147", "123457"));

        when(userTicketLottoRepository.findByUserTicketId("7852365147")).thenReturn(userTicketLottos);

        Map<String, Object> response = userService.getAllUserLotteries("7852365147");

        assertEquals("7852365147", response.get("User_ID"));
        assertEquals(List.of("123456", "123457"), response.get("Lottery_IDs"));
        assertEquals(2, response.get("Amount"));
        assertEquals(160, response.get("Cost"));
    }

    @Test
    @DisplayName("Should show Purchase canceled successfully")
    public void testCancelPurchase_Success() {

        List<UserTicketLotto> userTicketLottos = new ArrayList<>();
        UserTicketLotto userTicketLotto = new UserTicketLotto(1, "7852365147", "123456");
        userTicketLottos.add(userTicketLotto);

        when(userTicketLottoRepository.findUserTicketLottoByUserTicketIdAndLotteryId("7852365147", "123456")).thenReturn(userTicketLottos);

        ResponseEntity<?> response = userService.cancelPurchase("7852365147", "123456");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Purchase canceled successfully", response.getBody());
    }

    @Test
    @DisplayName("Should Throw Exception")
    public void testCancelPurchase_UserNotFound() {

        when(userTicketLottoRepository.findUserTicketLottoByUserTicketIdAndLotteryId("7852365147", "123456")).thenReturn(new ArrayList<>());


        assertThrows(BadRequestException.class, () -> userService.cancelPurchase("7852365147", "123456"));
    }


}
