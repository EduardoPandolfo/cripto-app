package com.eduardokp.criptoappsecurity.controllers;

import com.eduardokp.criptoappsecurity.entities.Coin;
import com.eduardokp.criptoappsecurity.repositories.CoinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.sql.Timestamp;

@RestController
@RequestMapping("/coin")
public class CoinController {

    private final Logger logger = LoggerFactory.getLogger(CoinController.class);

    @Autowired
    private CoinRepository repository;


    @PostMapping
    public ResponseEntity post(@RequestBody Coin coin) {
        try {
            coin.setDateTime(new Timestamp(System.currentTimeMillis()));
            Coin created = repository.insert(coin);
            return new ResponseEntity(created, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity("Error when creating coin, please contact support!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
