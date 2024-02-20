package com.kbtg.bootcamp.posttest.Lotto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LottoService {
    private List<Lotto> lottoList = new ArrayList<>(
            List.of(new Lotto(1,"000001",80),
                    new Lotto(2,"000002",80),
                    new Lotto(3,"123456",80))
    );

    public List<Lotto> getLottoList() {
        return lottoList;
    }

    public Map<String, List<String>> getTickets() {
        List<String> tickets = lottoList.stream()
                .map(Lotto::getTicket)
                .collect(Collectors.toList());

        Map<String, List<String>> ticketList = new HashMap<>();
        ticketList.put("tickets", tickets);
        return ticketList;
    }





    public Lotto createLotto(LottoRequestDto requestDto) throws Exception {
        Optional<Integer> maxId = lottoList.stream()
                .map(Lotto::getId)
                .max(Integer::compareTo);
        int nextId = maxId.orElse(0) + 1;
        Lotto lotto = new Lotto(nextId,requestDto.ticket(),requestDto.price());

        lottoList.add(lotto);
        return lotto;
    }



    public void deleteLottoById(@PathVariable Integer id) {
       lottoList.removeIf(user1 -> user1.getId().equals(id));
    }

    public void editLottoById(Integer id, LottoRequestDto requestDto) {
        for (Lotto lotto: lottoList) {
            if (lotto.getId().equals(id)) {
                lotto.setTicket(requestDto.ticket());
                break;
            }
        }
    }
}




