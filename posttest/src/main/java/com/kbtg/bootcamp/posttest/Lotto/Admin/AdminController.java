package com.kbtg.bootcamp.posttest.Lotto.Admin;

import com.kbtg.bootcamp.posttest.Lotto.Lotto;
import com.kbtg.bootcamp.posttest.Lotto.LottoRequest;
import com.kbtg.bootcamp.posttest.Lotto.LottoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final LottoService lottoService;

    public AdminController (LottoService lottoService) {this.lottoService = lottoService;}


    @PostMapping("/lotteries")  //create admin service
    public ResponseEntity<?> createUser(@Valid @RequestBody LottoRequest lottoRequest) {

            Lotto newLotto = lottoService.createAdminLotto(lottoRequest);
            return ResponseEntity.ok().body(newLotto);

    }

}
