package com.kbtg.bootcamp.posttest.UserTicketLotto;

import com.kbtg.bootcamp.posttest.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTicketLottoRepository extends JpaRepository<UserTicketLotto, Long> {


    @Query("SELECT utl FROM UserTicketLotto utl WHERE utl.user_ticket_id = :userTicketId AND utl.lottery_id = :lotteryId")
    List<UserTicketLotto> findUserTicketLottoByUserTicketIdAndLotteryId(@Param("userTicketId") String userTicketId, @Param("lotteryId") String lotteryId);
    @Query("SELECT utl FROM UserTicketLotto utl WHERE utl.user_ticket_id = :userId")
    List<UserTicketLotto> findByUserTicketId(@Param("userId") String userId);
}


