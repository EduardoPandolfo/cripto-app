package com.eduardokp.criptoapp.controllers;

import com.eduardokp.criptoapp.dtos.UserDTO;
import com.eduardokp.criptoapp.entities.User;
import com.eduardokp.criptoapp.repositories.UserRepository;
import com.eduardokp.criptoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody User user) {
        try {
            user.setEnabled(true);
            return new ResponseEntity<>(service.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> post(@PathVariable Long id, @RequestBody User user) {
        try {
            Optional<UserDTO> optUser = service.findById(id);
            if (optUser.isPresent()) {
                user.setId(id);
                return new ResponseEntity<>(service.save(user), HttpStatus.CREATED);
            }

            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> post(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
