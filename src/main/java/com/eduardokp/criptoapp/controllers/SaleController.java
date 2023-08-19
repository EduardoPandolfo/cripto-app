package com.eduardokp.criptoapp.controllers;

import com.eduardokp.criptoapp.dtos.SaleDTO;
import com.eduardokp.criptoapp.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService service;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody SaleDTO saleDTO) {

        try{
            long id = service.save(saleDTO);
            return new ResponseEntity<>("Venda realizada com sucesso: " +id, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error when creating sale", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
