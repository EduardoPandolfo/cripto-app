package com.eduardokp.criptoapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @JsonProperty(value = "product_id")
    private Long productId;
    @JsonProperty(value = "product_description")
    private String productDesc;
    private Integer quantity;
}
