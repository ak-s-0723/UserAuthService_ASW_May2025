package org.example.userauthservice_asw_may2025.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class User extends BaseModel {
    private String name;

    private String email;

    private String password;

    //If you want to add address and phoneNumber, please feel free to add

    public User() {
        this.setCreatedAt(new Date());
        this.setLastUpdatedAt(new Date());
        this.setState(State.ACTIVE);
    }
}
