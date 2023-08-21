package com.eduardokp.criptoapp.services;

import com.eduardokp.criptoapp.dtos.UserDTO;
import com.eduardokp.criptoapp.entities.User;
import com.eduardokp.criptoapp.repositories.UserRepository;
import com.eduardokp.criptoapp.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(this::mapingDTO).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public UserDTO save(User user) {
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        repository.save(user);
        return mapingDTO(user);
    }

    public Optional<UserDTO> findById(Long id) {
        Optional<User> optUser = repository.findById(id);

        if(optUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        return Optional.of(mapingDTO(optUser.get()));
    }

    private UserDTO mapingDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getUsername(), user.isEnabled());
    }

    public User getByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
