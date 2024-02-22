package com.kbtg.bootcamp.posttest.Lotto;


import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@RequestMapping("/lotteries")
public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {this.lottoService = lottoService;}



    @GetMapping("/info")
    public List<Lotto> getLottoList() {


        return this.lottoService.getLottoList();
    }

    @GetMapping("")
    public Map<String, List<String>> getTickets() {
        List<String> tickets = lottoService.getTickets();

        Map<String, List<String>> response = new HashMap<>();
        response.put("tickets", tickets);


        return response;
    }


    @PostMapping("")  //create admin service
    public ResponseEntity<?> createUser(@Valid @RequestBody LottoRequest lottoRequest) {
        try {
            Lotto newLotto = lottoService.createAdminLotto(lottoRequest);
            return ResponseEntity.ok().body(newLotto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        this.lottoService.deleteLottoById(id);
        return "Yaahoo";
    }



/*
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        lottoService.deleteLottoById(id);
    }

    @PutMapping("/{id}")
    public void editLotto(@PathVariable Integer id, @RequestBody LottoRequestDto requestDto) {
        lottoService.editLottoById(id, requestDto);
    }

 */
}








