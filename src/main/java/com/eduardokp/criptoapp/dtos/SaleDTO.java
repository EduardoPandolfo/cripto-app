package com.eduardokp.criptoapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleDTO {

    @JsonProperty(value ="user_id")
    private long userId;

    private List<ProductDTO> items;
}
