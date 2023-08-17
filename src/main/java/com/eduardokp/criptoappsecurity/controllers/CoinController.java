package com.eduardokp.criptoappsecurity.controllers;

import com.eduardokp.criptoappsecurity.dtos.CoinTransactionDTO;
import com.eduardokp.criptoappsecurity.entities.Coin;
import com.eduardokp.criptoappsecurity.repositories.CoinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coin")
public class CoinController {

    private final Logger logger = LoggerFactory.getLogger(CoinController.class);

    @Autowired
    private CoinRepository repository;

    @Bean
    public void init() {
        logger.info("-> INICIANDO COIN CONTROLLER");
//        Coin c1 = new Coin();
//
//        c1.setName("BITCOIN");
//        c1.setPrice(new BigDecimal("100.00"));
//        c1.setQuantity(new BigDecimal("3.0"));
//
//        Coin c2 = new Coin();
//
//        c2.setName("BITCOIN");
//        c2.setPrice(new BigDecimal("1020.00"));
//        c2.setQuantity(new BigDecimal("32.0"));
//
//        Coin c3 = new Coin();
//
//        c3.setName("ETHEREUM");
//        c3.setPrice(new BigDecimal("100.00"));
//        c3.setQuantity(new BigDecimal("3.0"));
//
//        repository.insert(c1);
//        repository.insert(c2);
//        repository.insert(c3);
    }


    @PostMapping
    public ResponseEntity<?> post(@RequestBody Coin coin) {
        try {
            Coin created = repository.insert(coin);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Error when creating coin, please contact support!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Coin coin) {
        try{
            return new ResponseEntity<>(repository.update(id, coin), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CoinTransactionDTO>> getAll() {
        return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<?> getByName(@PathVariable String nome) {
        try {
            return new ResponseEntity<>(repository.getByName(nome), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        boolean response = false;
        try{
            response = repository.deleteById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

}
