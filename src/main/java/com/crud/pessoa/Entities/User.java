package com.crud.pessoa.Entities;

import com.crud.pessoa.Repositories.UserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name  = "usuarios")
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;

    public  User(UserRequest userRequest) {
        this.id = userRequest.id();
        this.nome = userRequest.nome();
        this.email = userRequest.email();
    }

}


