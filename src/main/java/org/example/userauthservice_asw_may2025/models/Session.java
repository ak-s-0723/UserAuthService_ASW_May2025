package org.example.userauthservice_asw_may2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Session extends BaseModel {
    private String token;

    @ManyToOne
    private User user;
}

//1           1
//session    user
//m            1
//
//
//m : 1