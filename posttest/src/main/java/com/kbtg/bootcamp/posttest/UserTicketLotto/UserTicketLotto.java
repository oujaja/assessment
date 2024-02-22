package com.kbtg.bootcamp.posttest.UserTicketLotto;

import com.kbtg.bootcamp.posttest.Lotto.Lotto;
import com.kbtg.bootcamp.posttest.User.User;
import jakarta.persistence.*;


@Entity
@Table(name = "user_ticket_lottos")
public class UserTicketLotto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user_ticket_id;

    private String  lottery_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_ticket_id() {
        return user_ticket_id;
    }

    public void setUserTicketId(String user_ticket_id) {
        this.user_ticket_id = user_ticket_id;
    }

    public String getLottery_id() {
        return lottery_id;
    }

    public void setLotteryId(String lottery_id) {
        this.lottery_id = lottery_id;
    }


}

//add getter setter and repo