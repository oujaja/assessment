package com.kbtg.bootcamp.posttest.Lotto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;


@RestController
@RequestMapping("/lotteries")
public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    ;
    @GetMapping("")
    public Map<String, List<String>> getTickets() {
        return lottoService.getTickets();
    }

    @GetMapping("/info")
    public List<Lotto> getLottoList() {
        return lottoService.getLottoList();
    }



    @PostMapping("")  //create admin service
    public Lotto createUser(@Valid @RequestBody LottoRequestDto requestDto) throws Exception {
        return lottoService.createLotto(requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.lottoService.deleteLottoById(id);
    }

    @PutMapping("/{id}")
    public void editLotto(@PathVariable Integer id, @RequestBody LottoRequestDto requestDto) {
        this.lottoService.editLottoById(id, requestDto);
    }



}
record LottoRequestDto(
        @NotNull
        @Size(min = 6, max = 6, message = "Lotto length should be 6 characters")
        String ticket , int price) { }


