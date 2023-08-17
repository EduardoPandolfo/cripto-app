package com.eduardokp.criptoapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "price", precision = 20, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "quantity")
    @Min(value = 0)
    private Integer quantity;
}
