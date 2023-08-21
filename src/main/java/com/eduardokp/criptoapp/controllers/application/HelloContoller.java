package com.eduardokp.criptoapp.controllers.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloContoller {

    @GetMapping
    public ResponseEntity<?> getHello() {
        return new ResponseEntity<>("Hello, the application is online!", HttpStatus.OK);
    }

}
