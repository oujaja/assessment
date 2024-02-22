package com.kbtg.bootcamp.posttest.Lotto;

import jakarta.validation.Valid;
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

    public List<Lotto> getLottoList() {
        List<Lotto> lottoList = lottoRepository.findAll();
        return lottoList;
    }

    public List<String> getTickets() {
        List<Lotto> lottoList = lottoRepository.findAll();
        return lottoList.stream()
                .map(Lotto::getTicket)
                .collect(Collectors.toList());
    }

    public Lotto createAdminLotto(LottoRequest lottoRequest) {
        String ticket = lottoRequest.getTicket();
        int price = lottoRequest.getPrice();
        int amount = lottoRequest.getAmount();

        // Check for duplicate lottery IDs
        Optional<Lotto> existingLottos = lottoRepository.findByTicket(ticket);
        if (existingLottos.isPresent()) {
            throw new IllegalArgumentException("Duplicate lottery ID found.");
        }

        Lotto lotto = new Lotto();
        lotto.setTicket(lottoRequest.getTicket());
        lotto.setPrice(lottoRequest.getPrice());
        lotto.setAmount(1);
        lottoRepository.save(lotto);
        return lotto;
    }



    public void deleteLottoById(Integer id) {
        lottoRepository.deleteById(Long.valueOf(id));
    }
}


/*
    public void deleteLottoById(Integer id) {
        lottoRepository.deleteById(Long.valueOf(id));
    }

    public void editLottoById(Integer id, LottoRequestDto requestDto) {
        Optional<Lotto> optionalLotto = lottoRepository.findById(Long.valueOf(id));
        optionalLotto.ifPresent(lotto -> {
            lotto.setTicket(requestDto.ticket());
            lotto.setPrice(requestDto.price());
            lottoRepository.save(lotto);
        });
    }
}*/
