package com.eduardokp.criptoapp.controllers.application;

import com.eduardokp.criptoapp.dtos.LoginDTO;
import com.eduardokp.criptoapp.dtos.ResponseDTO;
import com.eduardokp.criptoapp.dtos.TokenDTO;
import com.eduardokp.criptoapp.security.CustomUserDetailService;
import com.eduardokp.criptoapp.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomUserDetailService detailService;

    @Autowired
    private JwtService jwtService;

    @Value("${security.jwt.expiration}")
    private String expirationInMinutes;

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            detailService.verifyUserCredentials(loginDTO);
            //when credentials are validated, create and return jwt token
            String token = jwtService.generateToken(loginDTO.getUsername());

            return new ResponseEntity<>(new TokenDTO(token, expirationInMinutes), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
