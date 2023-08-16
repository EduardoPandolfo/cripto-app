package com.eduardokp.criptoappsecurity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class CoinDTO {
    private String name;
    private BigDecimal quantity;
}
