package com.kbtg.bootcamp.posttest.User;


import com.kbtg.bootcamp.posttest.Lotto.Lotto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_ticket")
public class User {



    @Id
    private String id;

    private String name;


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}