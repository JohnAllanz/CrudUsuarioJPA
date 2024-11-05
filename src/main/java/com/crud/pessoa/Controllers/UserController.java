package com.crud.pessoa.Controllers;

import com.crud.pessoa.Entities.User;
import com.crud.pessoa.Repositories.UserRepository;
import com.crud.pessoa.Repositories.UserRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity createUser (@RequestBody UserRequest userRequest) {
        User user = new User(userRequest);
        userRepository.save(user);

        return  ResponseEntity.ok("salvo com sucesso");

    }

    @GetMapping
    public ResponseEntity ListUser(){
        var user = userRepository.findAll();

        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") UUID id) {

        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            userRepository.deleteById(id);

            return ResponseEntity.ok("deletado com sucesso");

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserRequest userRequest) {

        Optional<User> userOptional = userRepository.findById(userRequest.id());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNome(userRequest.nome());
            user.setEmail(userRequest.email());

            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }
}
