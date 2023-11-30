package com.example.sneakershop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int idUser;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InCart> inCart;
}
