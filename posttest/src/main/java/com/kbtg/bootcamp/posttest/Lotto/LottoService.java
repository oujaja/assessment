package com.kbtg.bootcamp.posttest.Lotto;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

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

    @Transactional
    public Lotto createAdminLotto(@Valid LottoRequest lottoRequest) {


        Lotto lotto = new Lotto();
        lotto.setTicket(lottoRequest.getTicket());
        lotto.setPrice(lottoRequest.getPrice());
        lotto.setAmount(1);
        lottoRepository.save(lotto);
        return null;
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
