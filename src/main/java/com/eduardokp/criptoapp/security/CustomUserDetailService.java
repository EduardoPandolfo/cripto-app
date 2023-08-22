package com.eduardokp.criptoapp.security;

import com.eduardokp.criptoapp.dtos.LoginDTO;
import com.eduardokp.criptoapp.entities.User;
import com.eduardokp.criptoapp.exceptions.PasswordInvalidException;
import com.eduardokp.criptoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.getByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Login was invalid");
        }

        return new UserPrincipal(user);
    }

    public void verifyUserCredentials(LoginDTO loginDTO) {
        UserDetails user = loadUserByUsername(loginDTO.getUsername());
        boolean isSamePassword = SecurityConfig.passwordEncoder().matches(loginDTO.getPassword(), user.getPassword());

        if(!isSamePassword) {
            throw new PasswordInvalidException();
        }

    }
}
