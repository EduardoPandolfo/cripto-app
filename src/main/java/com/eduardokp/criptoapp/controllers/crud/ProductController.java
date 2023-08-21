package com.eduardokp.criptoapp.controllers.crud;

import com.eduardokp.criptoapp.entities.Product;
import com.eduardokp.criptoapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Product Product) {
        try {
            return new ResponseEntity<>(repository.save(Product), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> post(@PathVariable Long id, @RequestBody Product Product) {
        try {
            Optional<Product> optProduct = repository.findById(id);
            if (optProduct.isPresent()) {
                Product.setId(id);
                return new ResponseEntity<>(repository.save(Product), HttpStatus.CREATED);
            }

            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> post(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>("Product deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

