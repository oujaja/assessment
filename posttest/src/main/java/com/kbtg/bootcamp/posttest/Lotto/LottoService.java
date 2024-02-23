package com.kbtg.bootcamp.posttest.Lotto;

import com.kbtg.bootcamp.posttest.Exception.BadRequestException;
import com.kbtg.bootcamp.posttest.Exception.InternalException;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class LottoService {

    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }


    //for checking only
    public List<Lotto> getLottoList() {
        try {
            List<Lotto> lottoList = lottoRepository.findAll();
            return lottoList;
        } catch (Exception e) {
            throw new InternalException("Internal server error occurred");
        }
    }

    public List<String> getTickets() {
        try {
            List<Lotto> lottoList = lottoRepository.findAll();
            return lottoList.stream()
                    .map(Lotto::getTicket)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalException("Internal Server Error occurred ");
        }
    }

    public Lotto createAdminLotto(LottoRequest lottoRequest) {
        String ticket = lottoRequest.getTicket();
        int price = lottoRequest.getPrice();
        int amount = lottoRequest.getAmount();

        // Check for duplicate lottery IDs
        Optional<Lotto> existingLottos = lottoRepository.findByTicket(ticket);
        if (existingLottos.isPresent()) {
            throw new BadRequestException("Duplicate lottery ID found.");
        }

        Lotto lotto = new Lotto();
        lotto.setTicket(lottoRequest.getTicket());
        lotto.setPrice(lottoRequest.getPrice());
        lotto.setAmount(1);
        lottoRepository.save(lotto);
        return lotto;
    }


    //addition feature
    public void deleteLottoById(Integer id) {
        lottoRepository.deleteById(Long.valueOf(id));
    }
}


