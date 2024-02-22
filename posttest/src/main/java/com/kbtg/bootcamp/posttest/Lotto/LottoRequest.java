package com.kbtg.bootcamp.posttest.Lotto;

public class LottoRequest {

    private String ticket;
    private int price;
    private int amount;


    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        if (ticket.length() != 6) {
            throw new IllegalArgumentException("Invalid lottery length. Expected length is 6.");
        }
        this.ticket = ticket;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}