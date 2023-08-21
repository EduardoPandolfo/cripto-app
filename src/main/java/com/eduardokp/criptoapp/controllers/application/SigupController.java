package com.eduardokp.criptoapp.controllers.application;

import com.eduardokp.criptoapp.entities.User;
import com.eduardokp.criptoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/sign-up")
public class SigupController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody User user) {
        try {
            user.setEnabled(true);
            return new ResponseEntity<>(service.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
