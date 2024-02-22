package com.kbtg.bootcamp.posttest.Lotto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface LottoRepository extends JpaRepository<Lotto, Long> {

    @Query("SELECT MAX(l.id) FROM Lotto l")
    Integer findMaxId();

    Optional<Lotto> findByTicket(String ticketId);
}