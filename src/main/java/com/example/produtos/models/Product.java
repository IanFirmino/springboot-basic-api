package com.example.produtos.models;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "product")
@EqualsAndHashCode(of = "id") @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price_in_cents;
    private boolean Active;

}
