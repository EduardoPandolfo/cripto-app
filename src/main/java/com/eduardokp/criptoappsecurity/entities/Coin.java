package com.eduardokp.criptoappsecurity.entities;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coin")
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "datetime")
    private Timestamp dateTime;

    @NotNull
    @Column(name = "price", scale = 20, precision = 2)
    private BigDecimal price;

    @NotNull
    @Column(name = "quantity", scale = 20, precision = 10)
    private BigDecimal quantity;

    public Coin(Long id) {
        this.id = id;
    }
}
