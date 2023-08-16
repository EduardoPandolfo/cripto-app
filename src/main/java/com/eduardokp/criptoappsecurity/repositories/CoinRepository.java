package com.eduardokp.criptoappsecurity.repositories;

import com.eduardokp.criptoappsecurity.entities.Coin;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@EnableAutoConfiguration
@Repository
public class CoinRepository {
    private static final String INSERT = "insert into coin(name, datetime, price, quantity) values (?,?,?,?)";

    private final JdbcTemplate template;

    public Coin insert(Coin coin) {
        Object[] attributes = new Object[] {
                coin.getName(),
                coin.getDateTime(),
                coin.getPrice(),
                coin.getQuantity()
        };

        int id = template.update(INSERT, attributes);

        coin.setId(Long.valueOf(id +""));

        return coin;
    }
}
