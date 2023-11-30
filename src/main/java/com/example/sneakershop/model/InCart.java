package com.example.sneakershop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "in_cart")

public class InCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "idCart")
    private Cart cart;
}
