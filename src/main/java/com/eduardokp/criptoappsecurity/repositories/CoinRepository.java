package com.eduardokp.criptoappsecurity.repositories;

import com.eduardokp.criptoappsecurity.dtos.CoinTransactionDTO;
import com.eduardokp.criptoappsecurity.entities.Coin;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@EnableAutoConfiguration
@Repository
public class CoinRepository {
    private static final String INSERT = "insert into coin(name, datetime, price, quantity) values (?,?,?,?);";
    private static final String SELECT_ALL = "select name, sum(quantity) as quantity from coin group by name order by name;";
    private static final String SELECT_BY_NAME = "select * from coin where name = ?;";
    private static final String DELETE = "delete from coin where id = ?;";
    private static final String UPDATE = "update coin set name = ?, price = ?, quantity = ?, datetime = ? where id = ?;";

    private final JdbcTemplate template;

    public Coin insert(Coin coin) {
        Object[] attributes = new Object[]{
                coin.getName(),
                new Timestamp(System.currentTimeMillis()),
                coin.getPrice(),
                coin.getQuantity()
        };

        template.update(INSERT, attributes);

        return coin;
    }

    public List<CoinTransactionDTO> getAll() {
        return template.query(SELECT_ALL, new RowMapper<CoinTransactionDTO>() {
            @Override
            public CoinTransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CoinTransactionDTO dto = new CoinTransactionDTO();
                dto.setName(rs.getString(1));
                dto.setQuantity(rs.getBigDecimal(2));
                return dto;
            }
        });
    }

    public List<Coin> getByName(String name) {
        Object[] attributes = new Object[]{name};

        return template.query(SELECT_BY_NAME, new RowMapper<Coin>() {
                    @Override
                    public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Coin coin = new Coin();
                        coin.setId(rs.getLong("id"));
                        coin.setName(rs.getString("name"));
                        coin.setQuantity(rs.getBigDecimal("quantity"));
                        coin.setPrice(rs.getBigDecimal("price"));
                        coin.setDateTime(rs.getTimestamp("datetime"));
                        return coin;
                    }
                },
                attributes);
    }

    public int deleteById(Long id) {
        return template.update(DELETE, id);
    }

    public int update(Long id, Coin coin) {
        Object[] attributes = new Object[] {
                coin.getName(),
                coin.getPrice(),
                coin.getQuantity(),
                coin.getDateTime(),
                id
        };
        return template.update(UPDATE, attributes);
    }
}
