package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.Exception.BadRequestException;
import com.kbtg.bootcamp.posttest.Exception.InternalException;
import com.kbtg.bootcamp.posttest.Lotto.Lotto;
import com.kbtg.bootcamp.posttest.Lotto.LottoRepository;
import com.kbtg.bootcamp.posttest.Lotto.LottoRequest;
import com.kbtg.bootcamp.posttest.Lotto.LottoService;


import com.kbtg.bootcamp.posttest.User.UserService;
import com.kbtg.bootcamp.posttest.UserTicketLotto.UserTicketLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LottoServiceTest {

    @Mock
    private LottoRepository lottoRepository;


    @InjectMocks
    private LottoService lottoService;

    @Test
    @DisplayName("Should show new lottery info")
    public void testCreateAdminLotto_Success() {
        LottoRequest lottoRequest = new LottoRequest("123456", 80, 1);


        when(lottoRepository.findByTicket("123456")).thenReturn(Optional.empty());


        Lotto createdLotto = lottoService.createAdminLotto(lottoRequest);

        assertNotNull(createdLotto);
        assertEquals("123456", createdLotto.getTicket());
        assertEquals(80, createdLotto.getPrice());
        assertEquals(1, createdLotto.getAmount());
    }

    @Test
    @DisplayName("Should throw Exception")
    public void testCreateAdminLotto_DuplicateTicket() {
        LottoRequest lottoRequest = new LottoRequest("123456", 80, 1);

        when(lottoRepository.findByTicket("123456")).thenReturn(Optional.of(new Lotto()));


        assertThrows(BadRequestException.class, () -> lottoService.createAdminLotto(lottoRequest));
    }

    @Test
    @DisplayName("Should ticket list")
    public void testGetTickets_Success() {

        List<Lotto> mockLottoList = Arrays.asList(
                new Lotto(1,"123456", 80),
                new Lotto(2,"789012", 80)
        );
        when(lottoRepository.findAll()).thenReturn(mockLottoList);


        List<String> tickets = lottoService.getTickets();

        assertEquals(2, tickets.size());
        assertTrue(tickets.contains("123456"));
        assertTrue(tickets.contains("789012"));
    }

    @Test
    @DisplayName("Should throw Exception")
    public void testGetTickets_InternalServerError() {

        when(lottoRepository.findAll()).thenThrow(new RuntimeException("Database connection failed"));


        assertThrows(InternalException.class, () -> lottoService.getTickets());
    }

}
