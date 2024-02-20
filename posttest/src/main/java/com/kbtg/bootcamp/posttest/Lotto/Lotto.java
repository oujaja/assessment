package com.kbtg.bootcamp.posttest.Lotto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//@Entity
//@Table(name = "lottery")
public class Lotto {

 //   @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min=6,max=6)
    private String ticket;

    private Integer price;

    public Lotto(Integer id, String ticket,Integer price) {
        this.id = id;
        this.ticket = ticket;
        this.price = price;
    }

    public Lotto() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

