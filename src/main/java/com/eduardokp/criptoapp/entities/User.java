package com.eduardokp.criptoapp.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    @NotBlank(message = "Name is obrigatory")
    private String name;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    @NotBlank(message = "Username is obrigatory")
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    @NotBlank(message = "Password is obrigatory")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Sale> sales;
}
