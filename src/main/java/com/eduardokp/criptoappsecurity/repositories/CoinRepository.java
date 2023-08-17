package com.eduardokp.criptoappsecurity.repositories;

import com.eduardokp.criptoappsecurity.dtos.CoinTransactionDTO;
import com.eduardokp.criptoappsecurity.entities.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
@EnableAutoConfiguration
public class CoinRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    public Coin insert(Coin coin) {
        coin.setDateTime(new Timestamp(System.currentTimeMillis()));
        //modelo de transacão
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        em.persist(coin);
//        transaction.commit();
//        return coin;

        em.persist(coin);
        return coin;
    }

    @Transactional
    public Coin update(Long id, Coin coin) {
        coin.setId(id);
        coin.setDateTime(new Timestamp(System.currentTimeMillis()));
        em.merge(coin);
        return coin;
    }

    public List<CoinTransactionDTO> getAll() {
        String jpql = "select new com.eduardokp.criptoappsecurity.dtos.CoinTransactionDTO(c.name, sum(c.quantity)) from Coin c group by c.name order by c.name";
        TypedQuery<CoinTransactionDTO> query = em.createQuery(jpql, CoinTransactionDTO.class);
        return query.getResultList();
    }

    public List<Coin> getByName(String name) {
        String jpql = "select c from Coin c where c.name like :name";
        TypedQuery<Coin> query = em.createQuery(jpql, Coin.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Transactional
    public boolean deleteById(Long id) {
        Coin coin = em.find(Coin.class, id);

        if(coin == null) {
            throw new RuntimeException("Coin not found");
        }

        em.remove(coin);

        return true;
    }


}
