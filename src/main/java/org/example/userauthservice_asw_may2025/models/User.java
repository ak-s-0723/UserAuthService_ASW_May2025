package org.example.userauthservice_asw_may2025.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class User extends BaseModel {
    private String name;

    private String email;

    private String password;

    //If you want to add address and phoneNumber, please feel free to add
}
