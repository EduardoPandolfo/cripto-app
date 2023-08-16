package com.eduardokp.criptoappsecurity.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coin {
    private Long id;
    private String name;
    private Timestamp dateTime;
    private BigDecimal price;
    private BigDecimal quantity;
}
