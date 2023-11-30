package com.example.sneakershop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String username;
    private String email;
    private String password;
    private boolean role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCart", referencedColumnName = "id")
    private Cart cart;

    public Account(int i, String username, String email, String password, boolean b) {
    }
}
